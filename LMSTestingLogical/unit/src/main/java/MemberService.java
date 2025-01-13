public class MemberService {

    public boolean registerMember(Member member) {
        if (member.getEmail() == null || member.getEmail().isEmpty()) {
            return false; // Fail registration if email is missing
        }
        // Assume other validations succeed
        return true;
    }
}

