4.1.6.RELEASE  

EmbeddedDB 사용법 http://www.mkyong.com/spring/spring-embedded-database-examples/
JUNIT의 다양한 사용법 
JAVA SPRING CONFIG
jackson
XML spring-oxm
HSQL, H2
Pojomatic
mybatis @MapperScan Transaction

## Spring xml로 사용할 경우
	src\main\resources\applicationContext.xml
	src\test\java\com\astral\embeddb\EmbeddedDB_XMLTest.java
	
## Spring code로 사용할 경우
	src\test\java\com\astral\embeddb\EmbeddedDBTest.java
	
## HSQL + @MapperScan + @Configuration
	@MapperScan을 사용하기 위해서는 xml 파일과 mapper 인터페이스가 같은 패키지 밑에 존재해야 한다. 
	
## Java로 스프링 컨피그 
	@Configuration
	@MapperScan("com.astraltear.embedded.hsql")
	@EnableTransactionManagement
	@ComponentScan(
		basePackages= {"com.astraltear.embedded.hsql.service"},
		useDefaultFilters=false,
		includeFilters= {@Filter(Service.class)}
	)
	

## @Transactional(propagation = Propagation.REQUIRED)
	propagation 정의
	REQUIRED : 기존 트랜잭션이 있을 때는 기존 트랜잭션 내에서 실행하고, 기존 트랜잭션이 없을 때 는 새로운 트랜잭션을 생성하여 실행한다.
	SUPPORTS : 새로운 트랜잭션이 필요하지는 않지만, 기존 트랜잭션이 있으면 해당 트랜잭션 내에서 메소드를 실행한다.
	MANDATORY : 반드시 트랜잭션 내에서 메소드가 실행되어야 한다. 만약 트랜잭션이 없다면 예외를 발생시킨다
	REQUIRES_NEW :  새로운 트랜잭션을 생성하여 실행한다. 기존의 트랜잭션이 있으면 기존 트랜잭션을 잠시 보류한다.
	NOT_SUPPORTED : 트랜잭션 없이 메소드를 실행하고, 기존 트랜잭션이 있으면 트랜잭션을 잠시 보류한다.
	NEVER : MANDATORY와는 반대로 트랜잭션 없이 실행되어야 한다. 만약 트랜잭션이 있다면 예외를 발생시킨다.
	NESTED : 트랜잭션이 있으면 기존 트랜잭션 내의 nested 트랜잭션 행태로 메소드를 실행한다. 자체적으로 Commit, Rollback이 가능하다. 트랜잭션이 없으면 REQUIRED 속성으로 실행한다.
	
	isolation level
	DEFAULT : 사용하는 데이터베이스에 의해 결정된다.
	READ_UNCOMMITTED : 다른 트랜잭션이 Commit하지 않은 데이터를 읽을 수 있다. (Dirty Read) 잠금/ 해제가 일어나지 않으므로 데이터의 일관성을 보장하지 않는다.
	READ_COMMITTED : 반드시 트랜잭션 내에서 메소드가 실행되어야 한다. 만약 트랜잭션이 없다면 예외를 발생시킨다
	REPEATABLE_READ : 다른 트랜잭션이 Commit하지 않은 데이터를 읽을 수 없다. 한 트랙잭션 내에서 동일 객체를 여러 번 조회할 때 다른 값을 읽을 수 있다. (Non-RepeatableRead) 대부분의 데이터베이스에서 기본으로 지원하는 격리 수준이다.
	NOT_SUPPORTED : 다른 트랜잭션이 Commit하지 않은 데이터를 읽을 수 없다. 한 트랜잭션 내에서 동일 객체를 여러 번 조회할 때 항상 같은 값을 읽는 것을 보장한다. (RepeatableRead)
	SERIALIZABLE : 가장 높은 격리 수준으로, 어떠한 간섭도 허용하지 않는다. 잠금/해제로 인한 비용이 많이 들지만 신뢰할 만한 격리 수준을 제공한다.
	
## JUNIT test 순서 변경
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	@Controller 테스트 