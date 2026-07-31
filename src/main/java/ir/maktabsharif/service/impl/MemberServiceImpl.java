package ir.maktabsharif.service.impl;

import ir.maktabsharif.exception.InvalidDataException;
import ir.maktabsharif.exception.MemberNotFoundException;
import ir.maktabsharif.model.Member;
import ir.maktabsharif.repository.MemberRepository;
import ir.maktabsharif.service.MemberService;

public class MemberServiceImpl extends BaseServiceImpl<Member,
        Long,
        MemberRepository,
        MemberNotFoundException>
        implements MemberService {
    public MemberServiceImpl(MemberRepository repository) {
        super(repository, () -> new MemberNotFoundException("Member not found!"));
    }

    @Override
    public void validation(Member member) {
        if (member.getFullName() == null || member.getFullName().isBlank()) {
            throw new InvalidDataException("Full name can not be null or empty!");
        }
        if (member.getNumber() == null || member.getNumber().isBlank()) {
            throw new InvalidDataException("Number can not be null or empty!");
        }
    }
}
