package com.shan.app.repository.cms.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shan.app.domain.GroupCode;
import com.shan.app.domain.QCode;
import com.shan.app.domain.QGroupCode;
import com.shan.app.repository.cms.GroupCodeRepositoryCustom;

public class GroupCodeRepositoryImpl implements GroupCodeRepositoryCustom {
	
	@Autowired
	private JPAQueryFactory queryFactory;
	
	@Override
	public GroupCode findGroupCodeWithCodesByQueryDsl(Long id) {
		QGroupCode qGroupCode = QGroupCode.groupCode1;
		QCode qCode = QCode.code1;
	
		return queryFactory.selectFrom(qGroupCode)
								.leftJoin(qGroupCode.codes, qCode).fetchJoin()
							.where(qGroupCode.id.eq(id))
							.fetchOne();
	}
}
