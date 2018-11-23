package util

import models.X12Constants._

trait X12Validator {
  def validateAndCreateISAHeader(isaHeaderLine: String) = {
    //ISA*00*          *00*          *ZZ*8431           *ZZ*ZIRMED         *130215*1234*^*00501*000000001*1*P*:~
    //control
    val fields: Array[String] = isaHeaderLine.split(SEPARATOR_STAR)

  }

  def validateAndCreateGSHeader(gsHeaderLine: String) = {

  }

}
