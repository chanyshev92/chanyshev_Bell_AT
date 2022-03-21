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
 * Класс с объектами ошибочного входа
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"error"
})
public class LoginUnSuccess {

@JsonProperty("error")
private String error;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
*
*/
public LoginUnSuccess() {
}

/**
*
* @param error Сообщение об ошибке
*/
public LoginUnSuccess(String error) {
super();
this.error = error;
}

@JsonProperty("error")
public String getError() {
return error;
}

@JsonProperty("error")
public void setError(String error) {
this.error = error;
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