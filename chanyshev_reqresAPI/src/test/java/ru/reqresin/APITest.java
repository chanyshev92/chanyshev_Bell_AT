package ru.reqresin;

import data.login.LoginData;
import data.login.LoginSuccess;
import data.login.LoginUnSuccess;
import data.unknownpage.UnknownPage;
import data.userspage.Datum;
import data.userspage.UsersPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static specifications.Specifications.*;

/**
 * Класс с тестами
 * @author Rustem Chanyshev
 * @version 1.0
 */
public class APITest {

    /**
     *Тест на проверку разных имен файлов аватаров пользовалей
     */
    @Test(testName = "Тест на проверку разных имен файлов аватаров пользовалей")
    public void testDistinctAvatarUsernames(){
        installSpec(requestSpec(),responseSpec());
        UsersPage usersPage=given()
                .when()
                .get("api/users?page=2")
                .then()
                .log().body()
                .extract()
                .body().as(UsersPage.class);
        long avatarFilenameCount = usersPage.getData()
                .stream()
                .map(Datum::getAvatar)
                .map(s->{String[] strings=s.split("/");
                return strings[strings.length-1];})
                .map(s -> {
                    String[] strings=s.split("\\.");
                    return strings[0];
                })
                .distinct()
                .count();
        Assert.assertEquals(avatarFilenameCount,usersPage.getData().size(),"Среди имен файлов аватаров на странице 2 есть повторяющиеся значения");

        deleteSpec();
    }

    /**
     * Тест на успешную аутентификацию пользователя
     */
    @Test(testName = "Тест на успешную аутентификацию пользователя")
    public void testSuccessfulAuth(){
        installSpec(requestSpec(),responseSpec());
        LoginData loginData =new LoginData("eve.holt@reqres.in","cityslicka");
        LoginSuccess loginSuccess=
                given()
                .body(loginData)
                .when()
                .post("/api/login")
                .then()
                .log().all()
                .extract()
                        .as(LoginSuccess.class);
        Assert.assertEquals(loginSuccess.getToken(),"QpwL5tke4Pnpja7X4","Авторизация не прошла или токен неверен");
        deleteSpec();

    }

    /**
     * Тест на провальную аутентификацию пользователя
     */
    @Test
    public void testUnsuccessfulAuth(){
        installSpec(requestSpec(),responseBadSpec());
        LoginData loginData =new LoginData("peter@klaven");
        LoginUnSuccess login = given()
                .body(loginData)
                .when()
                .post("/api/login")
                .then()
                .log().all()

                .extract().as(LoginUnSuccess.class);
        Assert.assertEquals(login.getError(),"Missing password","Авторизация не прошла или Сообщение об ошибке не выдано");


        deleteSpec();


    }

    /**
     * Тест на то, что года отсортированы
     */
    @Test
    public void testYearsSorted(){
        installSpec(requestSpec(),responseSpec());

        UnknownPage unknownPage = given()
                .when()
                .get("api/unknown")
                .then()
                .log().body()
                .extract()
                .body().as(UnknownPage.class);

        List<Integer> yearList = unknownPage.getData().stream().map(data.unknownpage.Datum::getYear).collect(Collectors.toList());

        Assert.assertEquals(yearList, yearList.stream().sorted().collect(Collectors.toList()), "Года в List<Resourse> не упорядочены");
        deleteSpec();

    }

}
