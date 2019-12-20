package com.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.model.ShipmentBean;
import com.util.IdGenerator;

public class MailSendingUtil {

	public static boolean sendMail(ShipmentBean shipmntBeanNew, ShipmentBean shipmntBeanOld) throws AddressException, MessagingException {
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("kewillmailtest@gmail.com", "test3214");
			}
		});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("kewillmailtest@gmail.com"));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(
				"shaini.sinha@blujaysolutions.com,sethu.pothuraju@blujaysolutions.com,ankush.khandelwal@blujaysolutions.com,Pavan.Chandraval@blujaysolutions.com"));
		message.setSubject("Approval Request");
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		String msg = null;
		if (shipmntBeanOld == null) {
			msg = "<i>Greetings!</i><br>";
			msg += "<b>Find the below Shipment details:</b><br>";
			msg += "<b>House Number: " + shipmntBeanNew.getHouseNo() + " </b><br>";
			msg += "<b>Shipment Ref Number: " + shipmntBeanNew.getShipmentRefNo() + " </b><br>";
			msg += "<b>Origin Branch: " + shipmntBeanNew.getOriginBranchDepartment() + " </b><br>";
			msg += "<b>Destination Branch: " + shipmntBeanNew.getDestinationBranchDepartment() + " </b><br>";
			msg += "<b>Consignee: " + shipmntBeanNew.getConsinee() + " </b><br>";
			msg += "<i>Thanks!</i><br><br>";
		} else if (shipmntBeanNew != null && shipmntBeanOld != null) {
			msg = "<i>Greetings!</i><br>";
			msg += "<b>Find the below Old Shipment details:</b><br>";
			msg += "<b>House Number: " + shipmntBeanOld.getHouseNo() + " </b><br>";
			msg += "<b>Shipment Ref Number: " + shipmntBeanOld.getShipmentRefNo() + " </b><br>";
			msg += "<b>Old Origin Branch: " + shipmntBeanOld.getOriginBranchDepartment() + " </b><br>";
			msg += "<b>Old Destination Branch: " + shipmntBeanOld.getDestinationBranchDepartment() + " </b><br>";
			msg += "<b>Old Consignee: " + shipmntBeanOld.getConsinee() + " </b><br>";
			
			msg += "<b><br>Find the below New Shipment details:</b><br>";
			msg += "<b>House Number: " + shipmntBeanNew.getHouseNo() + " </b><br>";
			msg += "<b>Shipment Ref Number: " + shipmntBeanNew.getShipmentRefNo() + " </b><br>";
			msg += "<b>New Origin Branch: " + shipmntBeanNew.getOriginBranchDepartment() + " </b><br>";
			msg += "<b>New Destination Branch: " + shipmntBeanNew.getDestinationBranchDepartment() + " </b><br>";
			msg += "<b>New Consignee: " + shipmntBeanNew.getConsinee() + " </b><br>";
			msg += "<i>Thanks!</i><br><br>";
		}
		
		String privateKey = IdGenerator.getSHA256Hash(shipmntBeanNew.getHouseNo());
		String approvaLlink = "http://10.148.4.156:8080/approve?requestId=" + privateKey + "&houseHold=" + shipmntBeanNew.getHouseNo();
		String rejectlink = "http://10.148.4.156:8080/reject?requestId=" + privateKey + "&houseHold=" + shipmntBeanNew.getHouseNo();
		
		mimeBodyPart.setContent(msg + "<br> To Accept: " + approvaLlink + "<br> <br> To Reject: " + rejectlink, "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);

		message.setContent(multipart);

		Transport.send(message);
		System.out.println("mail sent successfully!");
		return true;

	}

}
