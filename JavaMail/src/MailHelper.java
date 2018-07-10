import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
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

import com.sun.mail.smtp.SMTPMessage;

public class MailHelper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<String> mailsTo;
	
	private String subject;
	
	private String message;
	
	public boolean mail_smtp_useSSL = false;
	public String mail_smtp_user = "senhor.fluig@gmail.com";
	public String mail_smtp_password= "@dev1981";
	public String mail_smtp_host = "smtp.gmail.com";
	public String mail_smtp_port = "587";
	
	public MimeMessage getMimeMessage() {
		return mimeMessage;
	}

	public void setMimeMessage(MimeMessage mimeMessage) {
		this.mimeMessage = mimeMessage;
	}

	public String getSubject() {
		return subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMailsTo(List<String> mailsTo) {
		this.mailsTo = mailsTo;
	}

	public void setAttachments(List<AttachmentHelper> attachments) {
		this.attachments = attachments;
	}

	public void setMultipart(Multipart multipart) {
		this.multipart = multipart;
	}

	private List<AttachmentHelper> attachments;
	
	private MimeMessage mimeMessage;
	
	private Multipart multipart;
	
	public List<String> getMailsTo() {
		if (mailsTo == null) {
			mailsTo = new ArrayList<String>();
		}
		return mailsTo;
	}
	
	public Multipart getMultipart() {
		if (multipart == null) {
			multipart = new MimeMultipart();	
		}
		return multipart;
	}
	
	private List<AttachmentHelper> getAttachments() {
		if (attachments == null) {
			attachments = new ArrayList<MailHelper.AttachmentHelper>();
		}
		return attachments;
	}
	
	public static MailHelper newInstance() {
		return new MailHelper();
	}
	
	public void send() throws AddressException, MessagingException {
		createMimeMessage();
		configureSubject();
		addBodyMessage();
		addAttachments();
		getMimeMessage().setContent(getMultipart());
		Transport.send(getMimeMessage());
	}
	
	private void addBodyMessage() throws MessagingException {
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText(getMessage());
		getMultipart().addBodyPart(messageBodyPart);
	}
	
	private void addAttachments() throws MessagingException {
		if (!getAttachments().isEmpty()) {
			BodyPart messageBodyPart = null;
			DataSource source = null;
			for (AttachmentHelper attachmentHelper : getAttachments()) {
				messageBodyPart = new MimeBodyPart();
		        source = new FileDataSource(attachmentHelper.getAttachment());
		        messageBodyPart.setDataHandler(new DataHandler(source));
		        messageBodyPart.setFileName(attachmentHelper.getFilename());
		        getMultipart().addBodyPart(messageBodyPart);
			}
		}
	}
	
	private void configureSubject() throws AddressException, MessagingException {
		getMimeMessage().setFrom(new InternetAddress( mail_smtp_user ));
		for (String email : getMailsTo()) {
			getMimeMessage().addRecipient(Message.RecipientType.TO, new InternetAddress(email.trim()));
		}
		getMimeMessage().setSubject(subject);
	}
	
	public void createMimeMessage() {
		mimeMessage = new SMTPMessage(createSession());
	}
	
	public Session createSession() {
		Session session = null;
		
		Properties props = new Properties();
		props.put("mail.smtp.host", mail_smtp_host );
		props.put("mail.smtp.port", mail_smtp_port );
		props.put("mail.smtp.auth", "true");
		
		// adicional
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.starttls.enable", "true"); 
		
		if ( mail_smtp_useSSL ) {
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");	
		}
		
		session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(
						mail_smtp_user,
						mail_smtp_password);
			}
		});
		
		return session;
	}
	
	public MailHelper addEmailTo(String mailTo) {
		getMailsTo().add(mailTo);		
		return this;
	}
	
	public MailHelper setSubject(String subject) {
		this.subject = subject;
		return this;
	}
	
	public MailHelper setMessage(String message) {
		this.message = message;
		return this;
	}
	
	public MailHelper addAttachment(String filename, File attachment) {
		getAttachments().add(new AttachmentHelper(filename, attachment));
		return this;
	}
	
	class AttachmentHelper {
		
		public AttachmentHelper(String filename, File attachment) {
			this.filename = filename;
			this.attachment = attachment;
		}
		
		private String filename;
		
		public String getFilename() {
			return filename;
		}

		public void setFilename(String filename) {
			this.filename = filename;
		}

		public File getAttachment() {
			return attachment;
		}

		public void setAttachment(File attachment) {
			this.attachment = attachment;
		}

		private File attachment;
		
	}
	
	public static void main(String args[]) throws AddressException, MessagingException, IOException {
		MailHelper.newInstance()
			.addEmailTo("ricardo.aparecido.oliveira@gmail.com")
			.setSubject("Teste Mail Helper")
			.setMessage("Mensagem de teste")
			.addAttachment("teste", File.createTempFile("arquivo", "txt"))
			.send();
	}
	
}
