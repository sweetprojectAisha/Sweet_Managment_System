package MYApp_Sweet;

public class EmailService {
    public EmailService() {
    }

    // Method to send a welcome email
    public void sendWelcomeEmail(String email) {
        boolean emailSent = simulateEmailSending(email, "Welcome to Our Service", "Dear user, welcome to our service!");

        if (emailSent) {
            System.out.println("Welcome email successfully sent to: " + email);
        } else {
            System.out.println("Failed to send welcome email to: " + email);
        }
    }

    private boolean simulateEmailSending(String email, String subject, String body) {
        System.out.println("Sending email to: " + email);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);

        return true;
    }
}
