package dao

import javax.inject.Inject
import models.{Crypto, User}
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import play.api.libs.concurrent.Execution.Implicits._

import scala.concurrent.Future

class UserDAOImpl @Inject()(dbConfigProvider: DatabaseConfigProvider) extends UserDAO {

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._

  class UserTable(tag: Tag)
    extends Table[User](tag, "user") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def firstName = column[String]("first_name")

    def lastName = column[String]("last_name")

    def email = column[String]("email")

    def password = column[String]("password")

    override def * =
      (id, firstName, lastName, email, password) <> (User.tupled, User.unapply)
  }

  val users = TableQuery[UserTable]

  class CryptoTable(tag: Tag)
    extends Table[Crypto](tag, "crypto") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def userId = column[Long]("user_id")

    def name = column[String]("name")

    def price = column[String]("price")

    override def * =
      (id, userId, name, price) <> (Crypto.tupled, Crypto.unapply)


    def user = foreignKey("user_id", userId, users)(_.id)
  }

  val cryptos = TableQuery[CryptoTable]

  override def add(user: User): Future[String] = {
    db.run(users += user).map(res => "User successfully added").recover {
      case ex: Exception => ex.getCause.getMessage
    }
  }

  override def addCrypto(crypto: Crypto): Future[String] = {
    db.run(cryptos += crypto).map(res => "Crypto successfully added").recover {
      case ex: Exception => ex.getCause.getMessage
    }
  }

  override def updateCrypto(crypto: Crypto) = {
    db.run(cryptos.filter(_.id === crypto.id).update(crypto))
  }

  override def delete(id: Long): Future[Int] = {
    db.run(users.filter(_.id === id).delete)
  }

  override def get(id: Long): Future[Option[User]] = {
    db.run(users.filter(_.id === id).result.headOption)
  }

  override def getCrypto(id: Long): Future[Option[Crypto]] = {
    db.run(cryptos.filter(_.id === id).result.headOption)
  }

  override def listAll: Future[Seq[User]] = {
    db.run(users.result)
  }

  override def findByEmail(email: String): Future[Option[User]] = {
    db.run(users.filter(_.email === email).result.headOption)
  }

  override def getUserCryptos(id: Long): Future[Seq[Crypto]] = {
    db.run(cryptos.filter(_.userId === id).result)
  }

  override def deleteCrypto(id: Long): Future[Int] = {
    db.run(cryptos.filter(_.id === id).delete)
  }
}