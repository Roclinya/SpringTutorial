package com.tutorial.SpringTutorial.cache;

import com.tutorial.SpringTutorial.entity.Member;
import com.tutorial.SpringTutorial.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Component
public class TsmpDpItemsCacheProxy  extends DaoCacheProxy {

    @Autowired
    private MemberRepository memberRepository;
    protected MemberRepository getMemberRepository() {
        return this.memberRepository;
    }
    public Optional<Member> findById(Long id) {
        Supplier<Member> supplier = () -> {
            Optional<Member> opt = getMemberRepository().findById(id);
            return opt.orElse(null);
        };
        return getOne("findById", supplier, Member.class, id);
    }

//    public Optional<FeUsers> findById(Long id) {
//        Supplier<FeUsers> supplier = () -> {
//            Optional<FeUsers> opt = getFEUsersDao().findById(id);
//            return opt.orElse(null);
//        };
//        return getOne("findById", supplier, FeUsers.class, id);
//    }

    @Override
    protected Class<?> getDaoClass() {
        return MemberRepository.class;
    }

    @Override
    protected void kryoRegistration(String kryo) {
        // // 由子類別自行註冊要序列化的類別
//        kryo.register(TsmpDpItems.class);
    }

    @Override
    protected Consumer<String> getTraceLogger() {
        // TODO Auto-generated method stub
        return null;
    }
}
