package models

import play.api.data.Form
import play.api.data.Forms._

case class Crypto(val id: Long, userId: Long, name: String, price: String, shortcut: String)

case class CryptoFormData(name: String, price: String, shortcut: String)

object CryptoForm {

  val form = Form(
    mapping(
      "name" -> nonEmptyText,
      "price" -> nonEmptyText,
      "shortcut" -> nonEmptyText
    )(CryptoFormData.apply)(CryptoFormData.unapply)
  )
}
