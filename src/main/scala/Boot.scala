import util.X12Validator

object Boot extends App with X12Validator {
  val line = "ISA*00*          *00*          *ZZ*8431           *ZZ*ZIRMED         *130215*1234*^*00501*000000001*1*P*:~"

  validateAndCreateISAHeader(line)
}
