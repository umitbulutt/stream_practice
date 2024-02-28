package com.cydeo.streampractice.practice;

import com.cydeo.streampractice.model.*;
import com.cydeo.streampractice.service.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Component
public class Practice {

    public static CountryService countryService;
    public static DepartmentService departmentService;
    public static EmployeeService employeeService;
    public static JobHistoryService jobHistoryService;
    public static JobService jobService;
    public static LocationService locationService;
    public static RegionService regionService;

    public Practice(CountryService countryService, DepartmentService departmentService,
                    EmployeeService employeeService, JobHistoryService jobHistoryService,
                    JobService jobService, LocationService locationService,
                    RegionService regionService) {

        Practice.countryService = countryService;
        Practice.departmentService = departmentService;
        Practice.employeeService = employeeService;
        Practice.jobHistoryService = jobHistoryService;
        Practice.jobService = jobService;
        Practice.locationService = locationService;
        Practice.regionService = regionService;

    }

    // You can use the services above for all the CRUD (create, read, update, delete) operations.
    // Above services have all the required methods.
    // Also, you can check all the methods in the ServiceImpl classes inside the service.impl package, they all have explanations.

    // Display all the employees
    public static List<Employee> getAllEmployees() {
        return employeeService.readAll();
    }

    // Display all the countries
    public static List<Country> getAllCountries() {

        return countryService.readAll();
    }

    // Display all the departments
    public static List<Department> getAllDepartments() {
        //TODO Implement the method
        return departmentService.readAll();
    }

    // Display all the jobs
    public static List<Job> getAllJobs() {
        //TODO Implement the method
        return jobService.readAll();
    }

    // Display all the locations
    public static List<Location> getAllLocations() {
        //TODO Implement the method
        return locationService.readAll();
    }

    // Display all the regions
    public static List<Region> getAllRegions() {
        //TODO Implement the method
        return regionService.readAll();
    }

    // Display all the job histories
    public static List<JobHistory> getAllJobHistories() {
        //TODO Implement the method
        return jobHistoryService.readAll();
    }



    // Display all the employees' first names
    public static List<String> getAllEmployeesFirstName() {
        //TODO Implement the method
        List<String> names = new ArrayList<>();
        for (Employee eachEmployee : getAllEmployees()) {
            names.add(eachEmployee.getFirstName());
        }
        return names;

    }

    // Display all the countries' names
    public static List<String> getAllCountryNames() {
        //TODO Implement the method
        List<String> countryNames = new ArrayList<>();
        for (Country eachCountry : getAllCountries()) {
            countryNames.add(eachCountry.getCountryName());
        }

        return countryNames ;
    }

    // Display all the departments' managers' first names
    public static List<String> getAllDepartmentManagerFirstNames() {
        //TODO Implement the method
        List<String> departmentManagersFirstNames = new ArrayList<>();
        for (Department eachDepartment : getAllDepartments()) {
            departmentManagersFirstNames.add(eachDepartment.getManager().getFirstName());
        }

        return departmentManagersFirstNames;
    }

    // Display all the departments where manager name of the department is 'Steven'
    public static List<Department> getAllDepartmentsWhichManagerFirstNameIsSteven() {
        //TODO Implement the method
        List<Department> steveSDepartmens = new ArrayList<>();

        for (Department eachDepartment : getAllDepartments()) {
            if (eachDepartment.getManager().getFirstName().equalsIgnoreCase("Steven")){
                steveSDepartmens.add(eachDepartment);
            }
        }

        return steveSDepartmens;
    }

    // Display all the departments where postal code of the location of the department is '98199'
    public static List<Department> getAllDepartmentsWhereLocationPostalCodeIs98199() {
        //TODO Implement the method
        List<Department> departmentsList = new ArrayList<>();

        for (Department eachDepartment : getAllDepartments()) {
            if(eachDepartment.getLocation().getPostalCode().equalsIgnoreCase("98199")){
                departmentsList.add(eachDepartment);
            }
        }


        return departmentsList;
    }

    // Display the region of the IT department
    public static Region getRegionOfITDepartment() throws Exception {
        //TODO Implement the method
        Region regionIT = new Region();

        for (Department eachDepartment : getAllDepartments()) {
           if ( eachDepartment.getDepartmentName().equalsIgnoreCase("IT")){
               regionIT = eachDepartment.getLocation().getCountry().getRegion();
           }
        }


        return regionIT;
    }

    // Display all the departments where the region of department is 'Europe'
    public static List<Department> getAllDepartmentsWhereRegionOfCountryIsEurope() {
        //TODO Implement the method
        List<Department> departmentListOfEuropeRegion = new ArrayList<>();

        for (Department eachDepartment : getAllDepartments()) {
            if (eachDepartment.getLocation().getCountry().getRegion().getRegionName().equalsIgnoreCase("Europe")){
                departmentListOfEuropeRegion.add(eachDepartment);
            }
        }



        return departmentListOfEuropeRegion;
    }

    // Display if there is any employee with salary less than 1000. If there is none, the method should return true
    public static boolean checkIfThereIsNoSalaryLessThan1000() {
        //TODO Implement the method

      boolean isSalaryLessThan1000 =   getAllEmployees().stream()
                .anyMatch(s->s.getSalary()<1000);

      if (isSalaryLessThan1000==true){
          return false;
      }else {
          return true;
      }

    }

    // Check if the salaries of all the employees in IT department are greater than 2000 (departmentName: IT)
    public static boolean checkIfThereIsAnySalaryGreaterThan2000InITDepartment() {
        //TODO Implement the method

       boolean isGreater =  getAllEmployees().stream()
                .filter(s->s.getDepartment().getDepartmentName().equalsIgnoreCase("IT"))
                .anyMatch(s->s.getSalary()>2000);

        if (isGreater==false){
            return false;
        }else {
            return true;
        }
    }

    // Display all the employees whose salary is less than 5000
    public static List<Employee> getAllEmployeesWithLessSalaryThan5000() {
        //TODO Implement the method
        List<Employee> employeesWhoNeedRaise =getAllEmployees().stream()
                .filter(s->s.getSalary()<5000)
                .collect(Collectors.toList());




        return employeesWhoNeedRaise;
    }

    // Display all the employees whose salary is between 6000 and 7000
    public static List<Employee> getAllEmployeesSalaryBetween() {
        //TODO Implement the method
        List<Employee> listEmployeesAverage = getAllEmployees().stream()
                .filter(s->s.getSalary()>6000)
                .filter(s->s.getSalary()<7000)
                .collect(Collectors.toList());

        return listEmployeesAverage;
    }

    // Display the salary of the employee Grant Douglas (lastName: Grant, firstName: Douglas)
    public static Long getGrantDouglasSalary() throws Exception {
        //TODO Implement the method
        Optional<Long> salaryGrantDouglasOptional = getAllEmployees().stream()
                .filter(s -> s.getFirstName().equalsIgnoreCase("Douglas"))
                .filter(s -> s.getLastName().equalsIgnoreCase("Grant"))
                .map(Employee::getSalary)
                .findFirst();


        return salaryGrantDouglasOptional.orElse(0L);
    }

    // Display the maximum salary an employee gets
    public static Long getMaxSalary() throws Exception {
        Optional<Long> maxSalary = getAllEmployees().stream()
                .map(Employee::getSalary)
                .reduce(Long::max);

        return maxSalary.orElse(0L);
    }

    // Display the employee(s) who gets the maximum salary
    public static List<Employee> getMaxSalaryEmployee() {

        List<Employee> employeeMaxSalaries = getAllEmployees().stream()
                .map(Employee::getSalary)
                .max(Long::compare)
                .map(maxSalary -> getAllEmployees().stream()
                        .filter(employee -> employee.getSalary() == maxSalary)
                        .collect(Collectors.toList()))
                .orElse(List.of());

        return employeeMaxSalaries;
    }

    // Display the max salary employee's job
    public static Job getMaxSalaryEmployeeJob() throws Exception {
        //TODO Implement the method

       Job job =  getAllJobs().stream()
               .sorted(Comparator.comparing(Job::getMaxSalary).reversed())
               .findFirst()
               .orElse(null);

        return job;
    }

    // Display the max salary in Americas Region
    public static Long getMaxSalaryInAmericasRegion() throws Exception {
        //TODO Implement the method


       Optional <Long> maxSalaryInAmerica=  getAllEmployees().stream()
                .filter(s->s.getDepartment().getLocation().getCountry().getRegion().getRegionName().equalsIgnoreCase("Americas"))
                .map(Employee::getSalary)
                .reduce(Long::max)
                .stream().findFirst();

        return maxSalaryInAmerica.orElse(null);
    }

    // Display the second maximum salary an employee gets
    public static Long getSecondMaxSalary() throws Exception {
        //TODO Implement the method
        Optional <Long> secondMaxSalary=  getAllEmployees().stream()
                .map(Employee::getSalary)
                .skip(1)
                .reduce(Long::max)
                .stream().findFirst();

        return secondMaxSalary.orElse(null);

    }

    // Display the employee(s) who gets the second maximum salary
    public static List<Employee> getSecondMaxSalaryEmployee() {
        //TODO Implement the method


      Long secondMaxSalary=  getAllEmployees().stream()
              .map(Employee::getSalary)
              .distinct()
              .sorted(Comparator.reverseOrder())
              .skip(1)
              .findFirst()
              .orElse(null);

        List<Employee> secondMaxSalaryEmployee = getAllEmployees().stream()
                .filter(s->s.getSalary().equals(secondMaxSalary))
                .collect(Collectors.toList());

        return secondMaxSalaryEmployee;

    }

    // Display the minimum salary an employee gets
    public static Long getMinSalary() throws Exception {
        //TODO Implement the method

      Optional<Long>  minSalary =  getAllEmployees().stream()
                .map(Employee::getSalary)

                .reduce(Long::min);



        return minSalary.orElse(0L);
    }

    // Display the employee(s) who gets the minimum salary
    public static List<Employee> getMinSalaryEmployee() {
        //TODO Implement the method
        List<Employee> minSalaryEmployee = new ArrayList<>();

        try {
            Long  minSalary = getMinSalary();

            minSalaryEmployee = getAllEmployees().stream()
                    .filter(s->s.getSalary().equals(minSalary))
                    .collect(Collectors.toList());


            return minSalaryEmployee;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    // Display the second minimum salary an employee gets
    public static Long getSecondMinSalary() throws Exception {
        //TODO Implement the method

    Optional<Long> secondMinSalary =
            getAllEmployees().stream()
                 .map(Employee::getSalary)
                 .sorted()
                 .skip(1)
                 .findFirst();

        return secondMinSalary.orElse(0L);

    }

    // Display the employee(s) who gets the second minimum salary
    public static List<Employee> getSecondMinSalaryEmployee() {
        //TODO Implement the method

        return getAllEmployees().stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .skip(1)
                .limit(1)
                .collect(Collectors.toList());




    }

    // Display the average salary of the employees
    public static Double getAverageSalary() {
        //TODO Implement the method
        Double averageSalary = getAllEmployees().stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        return averageSalary;
    }

    // Display all the employees who are making more than average salary
    public static List<Employee> getAllEmployeesAboveAverage() {
        //TODO Implement the method
        List<Employee> employeesAboveAverage = getAllEmployees().stream()
                .filter(s->s.getSalary()>getAverageSalary())
                .collect(Collectors.toList());



        return employeesAboveAverage;
    }

    // Display all the employees who are making less than average salary
    public static List<Employee> getAllEmployeesBelowAverage() {
        //TODO Implement the method
        List<Employee> employeesAboveAverage = getAllEmployees().stream()
                .filter(s->s.getSalary()<getAverageSalary())
                .collect(Collectors.toList());

        return employeesAboveAverage;
    }

    // Display all the employees separated based on their department id number
    public static Map<Long, List<Employee>> getAllEmployeesForEachDepartment() {
        //TODO Implement the method
        Map<Long, List<Employee>> list = new HashMap<>();






        return new HashMap<>();
    }

    // Display the total number of the departments
    public static Long getTotalDepartmentsNumber() {
        //TODO Implement the method
        Long totalNumber = getAllDepartments().stream()
                .count();



        return totalNumber;
    }

    // Display the employee whose first name is 'Alyssa' and manager's first name is 'Eleni' and department name is 'Sales'
    public static Employee getEmployeeWhoseFirstNameIsAlyssaAndManagersFirstNameIsEleniAndDepartmentNameIsSales() throws Exception {
        //TODO Implement the method
        List<Employee> l = getAllEmployees().stream()
                .filter(s->s.getDepartment().getDepartmentName().equalsIgnoreCase("Sales"))
                .filter(s->s.getManager().getFirstName().equalsIgnoreCase("Eleni"))
                .filter(s->s.getFirstName().equalsIgnoreCase("Alyssa"))
                .collect(Collectors.toList());


        return  l.get(0);


    }

    // Display all the job histories in ascending order by start date
    public static List<JobHistory> getAllJobHistoriesInAscendingOrder() {
        //TODO Implement the method
        return new ArrayList<>();
    }

    // Display all the job histories in descending order by start date
    public static List<JobHistory> getAllJobHistoriesInDescendingOrder() {
        //TODO Implement the method
        return new ArrayList<>();
    }

    // Display all the job histories where the start date is after 01.01.2005
    public static List<JobHistory> getAllJobHistoriesStartDateAfterFirstDayOfJanuary2005() {
        //TODO Implement the method
        return new ArrayList<>();
    }

    // Display all the job histories where the end date is 31.12.2007 and the job title of job is 'Programmer'
    public static List<JobHistory> getAllJobHistoriesEndDateIsLastDayOfDecember2007AndJobTitleIsProgrammer() {
        //TODO Implement the method
        return new ArrayList<>();
    }

    // Display the employee whose job history start date is 01.01.2007 and job history end date is 31.12.2007 and department's name is 'Shipping'
    public static Employee getEmployeeOfJobHistoryWhoseStartDateIsFirstDayOfJanuary2007AndEndDateIsLastDayOfDecember2007AndDepartmentNameIsShipping() throws Exception {
        //TODO Implement the method
        return new Employee();
    }

    // Display all the employees whose first name starts with 'A'
    public static List<Employee> getAllEmployeesFirstNameStartsWithA() {
        //TODO Implement the method
        return new ArrayList<>();
    }

    // Display all the employees whose job id contains 'IT'
    public static List<Employee> getAllEmployeesJobIdContainsIT() {
        //TODO Implement the method
        return new ArrayList<>();
    }

    // Display the number of employees whose job title is programmer and department name is 'IT'
    public static Long getNumberOfEmployeesWhoseJobTitleIsProgrammerAndDepartmentNameIsIT() {
        //TODO Implement the method
        return 1L;
    }

    // Display all the employees whose department id is 50, 80, or 100
    public static List<Employee> getAllEmployeesDepartmentIdIs50or80or100() {
        //TODO Implement the method
        return new ArrayList<>();
    }

    // Display the initials of all the employees
    // Note: You can assume that there is no middle name
    public static List<String> getAllEmployeesInitials() {
        //TODO Implement the method
        return new ArrayList<>();
    }

    // Display the full names of all the employees
    public static List<String> getAllEmployeesFullNames() {
        //TODO Implement the method
        return new ArrayList<>();
    }

    // Display the length of the longest full name(s)
    public static Integer getLongestNameLength() throws Exception {
        //TODO Implement the method
        return 1;
    }

    // Display the employee(s) with the longest full name(s)
    public static List<Employee> getLongestNamedEmployee() {
        //TODO Implement the method
        return new ArrayList<>();
    }

    // Display all the employees whose department id is 90, 60, 100, 120, or 130
    public static List<Employee> getAllEmployeesDepartmentIdIs90or60or100or120or130() {
        //TODO Implement the method
        return new ArrayList<>();
    }

    // Display all the employees whose department id is NOT 90, 60, 100, 120, or 130
    public static List<Employee> getAllEmployeesDepartmentIdIsNot90or60or100or120or130() {
        //TODO Implement the method
        return new ArrayList<>();
    }

}
