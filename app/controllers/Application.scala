package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._

case class User(id:Int,name: String)

object Application extends Controller {

  implicit val userReads:Reads[User] =(
    (JsPath \ "id").read[Int] and
    (JsPath \ "name").read[String]
  )(User.apply _)

  def index = Action {
     Ok("""{"id":2,"name":"abcd"}""")
   // Ok("""{"user":{ "id":1,"name":"2"}}""")
  }

  def badResult = Action {
    BadRequest("")
  }

  def post = Action(parse.json){ request =>
        Ok("")
  }

  def helloWorld(name: String,city: String) = Action {
       Ok("hello "+name+","+city)
  }

}
