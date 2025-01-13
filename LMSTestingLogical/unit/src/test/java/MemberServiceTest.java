import org.testng.annotations.Test;
import static org.junit.Assert.*;

public class MemberServiceTest {

    private MemberService memberService = new MemberService();

    @Test
    public void testMemberRegistrationSuccess() {
        // Arrange
        Member member = new Member("John", "Doe", "john123", "password123", "12345", "john@example.com", "9876543210");

        // Act
        boolean result = memberService.registerMember(member);

        // Assert
        assertTrue("Registration should succeed for valid inputs", result);
    }

    @Test
    public void testMemberRegistrationMissingEmail() {
        // Arrange
        Member member = new Member("John", "Doe", "john123", "password123", "12345", null, "9876543210");

        // Act
        boolean result = memberService.registerMember(member);

        // Assert
        assertFalse("Registration should fail when email is missing", result);
    }
}
