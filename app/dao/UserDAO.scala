package dao

import com.google.inject.ImplementedBy
import models.{Crypto, User}

import scala.concurrent.Future

@ImplementedBy(classOf[UserDAOImpl])
trait UserDAO {
  def add(user: User): Future[String]

  def addCrypto(crypto: Crypto): Future[String]

  def get(id: Long): Future[Option[User]]

  def updateCrypto(crypto: Crypto)

  def getCrypto(id: Long): Future[Option[Crypto]]

  def delete(id: Long): Future[Int]

  def deleteCrypto(id: Long): Future[Int]

  def listAll: Future[Seq[User]]

  def getUserCryptos(id: Long): Future[Seq[Crypto]]

  def findByEmail(email: String): Future[Option[User]]
}
