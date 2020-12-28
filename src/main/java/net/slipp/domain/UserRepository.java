package net.slipp.domain;

import org.springframework.data.jpa.repository.JpaRepository;

//이 인터페이스 덕분에 데이터베이스에 데이터 추가하고 조회가능
public interface UserRepository extends JpaRepository<User,Long>{
	
	
}
