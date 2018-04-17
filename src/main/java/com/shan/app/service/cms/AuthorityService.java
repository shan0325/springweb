package com.shan.app.service.cms;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shan.app.domain.Authority;
import com.shan.app.repository.cms.AuthorityRepository;
import com.shan.app.service.cms.dto.AuthorityDTO.Create;
import com.shan.app.service.cms.dto.AuthorityDTO.Update;
import com.shan.app.web.errors.EntityDuplicatedException;
import com.shan.app.web.errors.EntityNotFoundException;

@Service("cmsAuthorityService")
public class AuthorityService {
	
	@Resource(name = "cmsAuthorityRepository")
	private AuthorityRepository authorityRepository;

	public Authority createAuthority(Create create) {
		Authority authorityDetail = authorityRepository.findOneByAuthority(create.getAuthority());
		if(authorityDetail != null) {
			throw new EntityDuplicatedException(Authority.class, "authority", create.getAuthority());
		}
		
		Authority authority = new Authority();
		authority.setAuthority(create.getAuthority());
		authority.setAuthorityName(create.getAuthorityName());
		
		return authorityRepository.save(authority);
	}

	public Authority updateAuthority(Long id, Update update) {
		Authority authority = getAuthority(id);
		authority.setAuthorityName(update.getAuthorityName());
		
		return authorityRepository.save(authority);
	}

	public Page<Authority> getAuthoritys(Pageable pageable) {
		return authorityRepository.findAll(pageable);
	}

	public Authority getAuthority(Long id) {
		Authority authority = authorityRepository.findOne(id);
		if(authority == null) {
			throw new EntityNotFoundException(Authority.class, "id", String.valueOf(id));
		}
		return authority;
	}

	public void deleteAuthority(Long id) {
		authorityRepository.delete(getAuthority(id));
	}

}
