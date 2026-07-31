package ir.maktabsharif.service;

import ir.maktabsharif.exception.MemberNotFoundException;
import ir.maktabsharif.model.Member;


public interface MemberService extends BaseService<Member , Long, MemberNotFoundException> {
}
