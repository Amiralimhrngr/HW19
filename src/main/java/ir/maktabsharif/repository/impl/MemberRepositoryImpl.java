package ir.maktabsharif.repository.impl;

import ir.maktabsharif.model.Member;
import ir.maktabsharif.repository.MemberRepository;
import jakarta.persistence.EntityManagerFactory;

public class MemberRepositoryImpl extends GenericRepositoryImpl<Member, Long> implements MemberRepository {
    public MemberRepositoryImpl(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    public Class<Member> getEntityClass() {
        return Member.class;
    }
}
