public interface MemberRepository {
    Member findByUsername(String username);
    void save(Member member);
}
