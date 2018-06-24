package services

import com.google.inject.ImplementedBy
import models.{Crypto, User}

import scala.concurrent.{ExecutionContext, Future}

@ImplementedBy(classOf[UserServiceImpl])
trait UserService {
  def addUser(user: User): Future[String]

  def addCrypto(crypto: Crypto): Future[String]

  def getUser(id: Long): Future[Option[User]]

  def getCrypto(id: Long): Future[Option[Crypto]]

  def deleteUser(id: Long): Future[Int]

  def deleteCrypto(id: Long): Future[Int]

  def getUserCryptos(id: Long) : Future[Seq[Crypto]]

  def updateCrypto(crypto: Crypto)

  def listAllUsers: Future[Seq[User]]

  def findByEmail(email: String) : Future[Option[User]]
}
