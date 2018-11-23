package models

//ISA
case class ISAHeader(interChangeControlHeader: String, authInfoQualifier: String, authInfo: String,
                     securityInfoQualifier: String, securityInfo: String, interChangeSenderIdQualifier: String,
                     interchangeSenderId: String, interChangeReceiverIdQualifier: String,
                     interchangeReceiverId: String, interchangeTime: String,
                     repetitionSep: String, interchangeControlVersion: String, interchangeControlNo: String,
                     ackReq: String, usageIndicator: String, componentElementSeparator: String)

//GS
case class GSHeader(functionalGroupHeader: String, functionalGroupIdCode: String, senderCode: String,
                    receiverCode: String, date: String, time: String, groupControlNo: String, agencyCode: String,
                    version: String)

//ST
case class TransactionHeader(transactionSetHeader: String, setIdentifier: String, controlNo: String,
                             conventionRef: String)

// Beginning of hierarchical information
case class BHT(bth: String, bhtCode: String, bthTransactionPurposeCode: String, referenceId: String, date: String,
               interchangeIdQualifier: String, transactionTypeCode: String)

//loop 1000A
case class SubmitterName(submitterName: String, entityIdCode: String, entityTypeQualifier: Int,
                         lastNameOrgName: String, firstName: Option[String], middleName: Option[String],
                         namePrefix: String, nameSuffix: String, idCodeQualifier: String, idCode: String)

case class CommunicationDetails(communicationNoQualifier: String, communicationNumber: String)

//PER
case class SubmitterContactInfo(submitterContactInfo: String, contactFunctionCode: String, Name: String,
                                communicationDetails: List[CommunicationDetails])

//loop 1000B SubmitterName can be used
case class ReceiverName(receiverName: String, entityIdCode: String, entityTypeQualifier: Int,
                        lastNameOrgName: String, firstName: Option[String], middleName: Option[String],
                        namePrefix: String, nameSuffix: String, idCodeQualifier: String, idCode: String)

//Billing Provider Hierarchical Level
case class HLHeader(HLHeader: String, HLIdNumber: Int, HLParentIdNumber: Int, HLCode: Int, HLChildCode: Int)

// loop 2010AA SubmitterName can be used
case class BillingProvider(billingProvider: String, entityIdCode: String, entityTypeQualifier: Int,
                           lastNameOrgName: String, firstName: Option[String], middleName: Option[String],
                           namePrefix: String, nameSuffix: String, idCodeQualifier: String, idCode: String)

//N3
case class BillingProviderAddress(billingProviderAddress: String, addressLine1: String, addressLine2: Option[String])

//N4
case class BillingProviderCityDetail(detailCode: String, cityName: String, state: String, postalCode: String)


//loop 2000B HLHeader can be used
case class SubscriberHL(subscriberHL: String, subscriberID: Int, subscriberParentId: Int, HLCode: Int, HLChildCode: Int)

//TODO SBR
case class SubscriberInfo(subscriberInfo: String, payerRespCode: String, individualRelationCode: Option[String],
                          refId: Option[String], name: Option[String], insuranceType: Option[Int],
                          coordinationOfBenefit: String, condition: Boolean, EmployeeStatusCode: String,
                          claimFiling: Option[String])

//subscriber Demographic Information
case class DMG(dmg: String, dateTimeQualifier: String, dateTime: String, genderCode: String)

//loop 2000C Patient Information
case class PAT(patientInfo: String, IndividualRelationShip: String, patientLocationCode: String, EmpStatusCode: String,
               StudentStatusCode: String, dateTimeQualifier: String, dateTime: String, measurementCode: String,
               weight: String, condition: Boolean)

//CLM
case class ClaimInfo(clm: String, clmSubmitterId: String, monetaryAmount: String, clmFillingIndicator: String,
                     nonInstitutionalClaimType: String, facilityCode: String, claimFreqTypeCode: String,
                     signature: Boolean, providerAcceptAssignment: String, AssignmentBenefits: Boolean,
                     releaseInfo: Boolean, patientSignatureSourceCode: String)

//REF
case class ReplacementClaim(cliaNumber: String, referenceIdQualifier: String, referenceId: String)

case class DTP(dateIllness: String, dateTimeQualifier: String, dateTimeFormatQualifier: String, dateTime: String)

/*case class NM1(per: Option[SubmitterContactInfo], hl: Option[HLHeader], n3: Option[BillingProviderAddress],
               n4: Option[BillingProviderCityDetail], ref: Option[ReplacementClaim], sbr: Option[SubscriberInfo],
               dmg: Option[DMG], pat: Option[PAT], clm: Option[ClaimInfo], hi: Option[HI], lx: Option[LX],
               sv1: Option[SV1], dtp: Option[DTP])

case class ST_SE(controlNumber: String, nm1s: List[NM1])

case class GS_GE(controlNumber: String, stSes: List[ST_SE])

case class ISA_IEA(controlNumber: String, gsGes: List[GS_GE])*/

object Constants {
  val validTransactionCodeBHT = List("31", "CH", "RP")
  val validTransactionPurposeCode = List("00", "18")
  val validEntityTypeQualifierSubmitterName = List(1, 2)

  /*
  *
  * ED: Electronic data interchange Access Number
  * Electronic Mail
  * Telephone Extension
  * Facsimile
  * Telephone
  *
  * */
  val validCommunicationQualifier = List("ED", "EM", "EX", "FX", "TE")
  val payerResponsibilitySeqCode = List("P", "S", "T", "A", "B", "C", "D", "E", "F", "G", "H")
  val validInsuranceTypeCode = List(12, 13, 14, 15, 16, 41, 42, 43, 47)
  val validClaimFilingIndicator = List("11", "12", "13", "14", "15", "16", "17", "AM", "BL", "CH", "CI", "DS", "HM",
    "LM", "MB", "MC", "MA", "OF", "TV", "VA", "WC", "FI", "ZZ")
  val validRelationShipCode = List("01", "19", "20", "21", "39", "40", "53", "G8")
}
