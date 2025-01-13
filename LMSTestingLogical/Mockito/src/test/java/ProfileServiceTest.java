import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ProfileServiceTest {

    // Mock the repository dependency
    @Mock
    private MemberRepository memberRepository;

    // Inject the mock repository into the service
    @InjectMocks
    private ProfileService profileService;

    // Initialize the mocks before each test
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);  // This initializes the @Mock and @InjectMocks annotations
    }

    @Test
    public void testProfileUpdateWithMockDatabase() {
        // Arrange: Create a mock member
        Member mockMember = new Member("Amali", "Ganegoda", "amali123", "password123", "amali@example.com");

        // Define the mock behavior: When findByUsername is called, return the mock member
        when(memberRepository.findByUsername("amali123")).thenReturn(mockMember);

        // Act: Update the member's email
        mockMember.setEmail("newemail@example.com");
        profileService.updateProfile(mockMember);

        // Assert: Verify that the repository's save method was called with the updated member
        verify(memberRepository).save(mockMember);

        // Assert that the member's email was correctly updated
        assertEquals("newemail@example.com", mockMember.getEmail());
    }
}

