package nl.hva.dogwalker.persistence.repository;

import nl.hva.dogwalker.business.domain.Customer;
import nl.hva.dogwalker.business.domain.Dog;
import nl.hva.dogwalker.persistence.dao.CustomerDao;
import nl.hva.dogwalker.persistence.dao.DogDao;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerRepositoryTest {

  private CustomerRepository customerRepositoryUnderTest;
  private CustomerDao customerDaoMock;
  private DogDao dogDaoMock;
  private Customer testCustomer;
  private Dog boelie;
  private Dog wiske;
  private Dog luna;
  private List<Dog> testDogList;

  @BeforeAll
  public void setUp() {
    dogDaoMock = Mockito.mock(DogDao.class);
    customerDaoMock = Mockito.mock(CustomerDao.class);
    customerRepositoryUnderTest = new CustomerRepository(customerDaoMock, dogDaoMock);
  }

  @BeforeEach
  public void initializeTestData() {
    // A test may modify test data (e.g. by setting id's or modyfying lists)
    // This mthod is called before each test so each test starts with unmodified data
    testCustomer = new Customer("Gerke", "gerke@universe.un");
    boelie = new Dog("Boelie", "Franse Bulldog", null);
    wiske = new Dog("Wiske", "Boxer", null);
    luna = new Dog("Luna", "mix", null);
    testDogList = List.of(wiske, boelie);
    Mockito.when(customerDaoMock.findCustomerById(Mockito.anyInt())).thenReturn(Optional.empty());
    Mockito.when(customerDaoMock.findCustomerById(1)).thenReturn(Optional.of(testCustomer));
    Mockito.when(dogDaoMock.findAllDogsForCustomer(1)).thenReturn(testDogList);

    Mockito.doAnswer(invocationOnMock -> {
      Customer customer = (Customer) invocationOnMock.getArgument(0);
      customer.setId(1);
      return null;
    }).when(customerDaoMock).save(testCustomer);
  }

  @AfterAll
  static void tearDown() {
  }

  @Test
  void findCustomerByIdWithDogs() {
    Customer actual = customerRepositoryUnderTest.findCustomerByIdWithDogs(1);
    Customer expected = testCustomer;
    expected.setDogs(testDogList);
    assertThat(actual).isNotNull().isEqualTo(expected);
    assertThat(actual.getDogs()).contains(boelie, wiske);
    assertThat(actual.getDogs().get(0).getOwner()).isNotNull().isEqualTo(testCustomer);
  }

  @Test
  void save() {
    assertThat(testCustomer.getId()).isEqualTo(0);
    customerRepositoryUnderTest.save(testCustomer);
    assertThat(testCustomer.getId()).isEqualTo(1);
  }

  @Test
  void findCustomerById() {
    Customer actual = customerRepositoryUnderTest.findCustomerById(1);
    Customer expected = testCustomer;
    assertThat(actual).isNotNull().isEqualTo(expected);
  }
}