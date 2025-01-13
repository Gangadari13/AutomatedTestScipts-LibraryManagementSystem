public class ProfileService {

    private MemberRepository memberRepository;

    // Constructor
    public ProfileService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void updateProfile(Member member) {
        memberRepository.save(member);  // Save updated profile to the repository
    }
}


