package services

import javax.inject.Inject
import dao.UserDAO
import models.{Crypto, User}

import scala.concurrent.Future

class UserServiceImpl @Inject()(userDAO: UserDAO) extends UserService {
  override def addUser(user: User): Future[String] = {
    userDAO.add(user)
  }

  override def deleteUser(id: Long): Future[Int] = {
    userDAO.delete(id)
  }

  override def getUser(id: Long): Future[Option[User]] = {
    userDAO.get(id)
  }

  override def getCrypto(id: Long): Future[Option[Crypto]] = {
    userDAO.getCrypto(id)
  }

  override def listAllUsers: Future[Seq[User]] = {
    userDAO.listAll
  }

  override def findByEmail(email: String): Future[Option[User]] = {
    userDAO.findByEmail(email)
  }

  override def addCrypto(crypto: Crypto): Future[String] = {
    userDAO.addCrypto(crypto)
  }

  override def getUserCryptos(id: Long): Future[Seq[Crypto]] = {
    userDAO.getUserCryptos(id)
  }

  override def updateCrypto(crypto: Crypto): Unit = {
    userDAO.updateCrypto(crypto)
  }

  override def deleteCrypto(id: Long): Future[Int] = {
    userDAO.deleteCrypto(id)
  }
}
