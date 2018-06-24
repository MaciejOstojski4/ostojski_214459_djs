package models

import play.api.data.Form
import play.api.data.Forms._

case class Crypto(val id: Long, userId: Long, name: String, price: String)

case class CryptoFormData(name: String, price: String)

object CryptoForm {

  val form = Form(
    mapping(
      "name" -> nonEmptyText,
      "price" -> nonEmptyText
    )(CryptoFormData.apply)(CryptoFormData.unapply)
  )
}
