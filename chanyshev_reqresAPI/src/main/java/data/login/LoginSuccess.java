package data.login;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Класс с объектами успешного входа
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"token"
})
public class LoginSuccess {

@JsonProperty("token")
private String token;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
*
*/
public LoginSuccess() {
}

/**
*
* @param token Токен авторизации конкретного пользователя
*/
public LoginSuccess(String token) {
super();
this.token = token;
}

@JsonProperty("token")
public String getToken() {
return token;
}

@JsonProperty("token")
public void setToken(String token) {
this.token = token;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}