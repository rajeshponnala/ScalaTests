import controllers._
import scala.concurrent.Future
import org.scalatest._
import org.scalatestplus.play._
import play.api.mvc._
import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json._
import play.api.libs.functional.syntax._

class ApplicationControllerSpec extends PlaySpec with Results {
   implicit val userReads:Reads[User] =(
    (JsPath \ "id").read[Int] and
    (JsPath \ "name").read[String]
   )(User.apply _)

  "this action" should { "return valid user" in {
   val res = Application.index.apply(FakeRequest())
      contentAsJson(res).validate[User].map(_ => true).getOrElse(false) mustBe true
      }
  }

  "this Action" should { "return badresult" in {
    val res = Application.badResult.apply(FakeRequest())
     status(res) mustEqual BAD_REQUEST
  }

    "this action  without data" should { "return badrequest" in {
      val fakeReq = FakeRequest(POST,"/")
      val res = call(Application.post,fakeReq)
      status(res) mustEqual BAD_REQUEST
     }

    }

    " this action with param  name=Rajesh and city=Hyderabad" should {"return hello Rajesh,Hyderabad" in {
        val res = Application.helloWorld("Rajesh","Hyderabad").apply(FakeRequest())
        contentAsString(res) mustEqual "hello Rajesh,Hyderabad"
    }

    }

  }

  }
