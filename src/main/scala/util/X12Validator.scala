package util

import models.ISAHeader
import models.X12Constants._

trait X12Validator {

  private def authOrSecurityInfoIsValid(authInfoQualifier: String, authInfo: String): Boolean = {
    (authInfoQualifier == "00" && authInfo == BLANK_10) || (authInfoQualifier == "03" && authInfo.length == 10 &&
      authInfo != BLANK_10)
  }

  private def securityInfoQualifierIsValid(authInfoQualifier: String, securityInfoQualifier: String): Boolean = {
    (authInfoQualifier == "00" && securityInfoQualifier == "00") || (authInfoQualifier == "03" &&
      securityInfoQualifier == "01")
  }

  private def interchangeIdQualifierIsValid(interChangeIdQualifier: String): Boolean = {
    val onlyDigitsRegex: String = "^\\d+$"

    interChangeIdQualifier.length == 2 && interChangeIdQualifier.matches(onlyDigitsRegex)
  }

  private def interchangeSenderOrReceiverIdIsValid(interchangeSenderId: String): Boolean = {
    interchangeSenderId.nonEmpty && (interchangeSenderId.length == 2 || interchangeSenderId.length == 15)
  }

  def validateAndCreateISAHeader(isaHeaderLine: String): Option[ISAHeader] = {
    //ISA*00*          *00*          *ZZ*8431           *ZZ*ZIRMED         *130215*1234*^*00501*000000001*1*P*:~
    if (isaHeaderLine.endsWith(SEGMENT_TERMIANTOR) && isaHeaderLine.length == 106) {
      val fields: Array[String] = isaHeaderLine.dropRight(1).split(SEPARATOR_STAR)
      fields match {
        case Array(interChangeControlHeader, authInfoQualifier, authInfo, securityInfoQualifier, securityInfo,
        interChangeSenderIdQualifier, interchangeSenderId, interChangeReceiverIdQualifier, interchangeReceiverId,
        interchangeTime, repetitionSep, interchangeControlVersion, interchangeControlNo, ackReq, usageIndicator, componentElementSeparator) =>

          if (interChangeControlHeader == "ISA" && authOrSecurityInfoIsValid(authInfoQualifier, authInfo) &&
            securityInfoQualifierIsValid(authInfoQualifier, securityInfoQualifier) &&
            authOrSecurityInfoIsValid(authInfoQualifier, securityInfo) &&
            interchangeIdQualifierIsValid(interChangeSenderIdQualifier) &&
            interchangeSenderOrReceiverIdIsValid(interchangeSenderId) &&
            interchangeIdQualifierIsValid(interChangeReceiverIdQualifier) &&
            interchangeSenderOrReceiverIdIsValid(interchangeReceiverId) &&

          ) {
            Some(ISAHeader(interChangeControlHeader, authInfoQualifier, authInfo, securityInfoQualifier, securityInfo,
              interChangeSenderIdQualifier, interchangeSenderId, interChangeReceiverIdQualifier, interchangeReceiverId,
              interchangeTime, repetitionSep, interchangeControlVersion, interchangeControlNo, ackReq, usageIndicator, componentElementSeparator))
          } else None

        case _ => None
      }
    } else None
  }

  def validateAndCreateGSHeader(gsHeaderLine: String) = {

  }

}
