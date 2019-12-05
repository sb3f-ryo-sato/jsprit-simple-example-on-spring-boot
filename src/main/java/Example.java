/* ****************************************************************
 * 4.3. Writing the Code - Getting Started
 * https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html#getting-started-first-application-code
 **************************************************************** */

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

import com.graphhopper.jsprit.core.algorithm.VehicleRoutingAlgorithm;
import com.graphhopper.jsprit.core.algorithm.box.Jsprit;
import com.graphhopper.jsprit.core.problem.Location;
import com.graphhopper.jsprit.core.problem.VehicleRoutingProblem;
import com.graphhopper.jsprit.core.problem.job.Service;
import com.graphhopper.jsprit.core.problem.solution.VehicleRoutingProblemSolution;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleImpl;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleType;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleTypeImpl;
import com.graphhopper.jsprit.core.reporting.SolutionPrinter;
import com.graphhopper.jsprit.core.util.Solutions;
import com.graphhopper.jsprit.io.problem.VrpXMLWriter;

import java.util.Collection;

@RestController
@EnableAutoConfiguration
public class Example {

    @RequestMapping("/")
    String home() {

	/* ****************************************************************
	 * jsprit/Simple-Example.md at master - graphhopper/jsprit - GitHub
	 * https://github.com/graphhopper/jsprit/blob/master/docs/Simple-Example.md
	 **************************************************************** */

	/*
	 * get a vehicle type-builder and build a type with the typeId "vehicleType" and a capacity of 2
	 * you are free to add an arbitrary number of capacity dimensions with .addCapacityDimension(dimensionIndex,dimensionValue)
	 */
	final int WEIGHT_INDEX = 0;
	VehicleTypeImpl.Builder vehicleTypeBuilder = VehicleTypeImpl.Builder.newInstance("vehicleType").addCapacityDimension(WEIGHT_INDEX,2);
	VehicleType vehicleType = vehicleTypeBuilder.build();

	/*
	 * get a vehicle-builder and build a vehicle located at (10,10) with type "vehicleType"
	 */
	VehicleImpl.Builder vehicleBuilder = VehicleImpl.Builder.newInstance("vehicle");
	vehicleBuilder.setStartLocation(Location.newInstance(10, 10));
	vehicleBuilder.setType(vehicleType);
	VehicleImpl vehicle = vehicleBuilder.build();


	/*
	 * build services with id 1...4 at the required locations, each with a capacity-demand of 1.
	 * Note, that the builder allows chaining which makes building quite handy
	 */
	Service service1 = Service.Builder.newInstance("1").addSizeDimension(WEIGHT_INDEX,1).setLocation(Location.newInstance(5, 7)).build();
	Service service2 = Service.Builder.newInstance("2").addSizeDimension(WEIGHT_INDEX,1).setLocation(Location.newInstance(5, 13)).build();
	Service service3 = Service.Builder.newInstance("3").addSizeDimension(WEIGHT_INDEX,1).setLocation(Location.newInstance(15, 7)).build();
	Service service4 = Service.Builder.newInstance("4").addSizeDimension(WEIGHT_INDEX,1).setLocation(Location.newInstance(15, 13)).build();


	/*
	 * again define a builder to build the VehicleRoutingProblem
	 */
	VehicleRoutingProblem.Builder vrpBuilder = VehicleRoutingProblem.Builder.newInstance();
	vrpBuilder.addVehicle(vehicle);
	vrpBuilder.addJob(service1).addJob(service2).addJob(service3).addJob(service4);
	/*
	 * build the problem
	 * by default, the problem is specified such that FleetSize is INFINITE, i.e. an infinite number of
	 * the defined vehicles can be used to solve the problem
	 * by default, transport costs are computed as Euclidean distances
	 */
	VehicleRoutingProblem problem = vrpBuilder.build();


	/*
	 * get the algorithm out-of-the-box.
	 */
	VehicleRoutingAlgorithm algorithm = Jsprit.createAlgorithm(problem);

	/*
	 * and search a solution which returns a collection of solutions (here only one solution is constructed)
	 */
	Collection<VehicleRoutingProblemSolution> solutions = algorithm.searchSolutions();

	/*
	 * use the static helper-method in the utility class Solutions to get the best solution (in terms of least costs)
	 */
	VehicleRoutingProblemSolution bestSolution = Solutions.bestOf(solutions);


	/*
	 * If you want to have more information about individual routes, use the verbose level such as
	 */
	SolutionPrinter.print(problem, bestSolution, SolutionPrinter.Print.VERBOSE);


	/*
	 * If you want to write out problem and solution, you require the writer that is located in jsprit-io.
	 * Thus, you need to add the corresponding module to your pom.xml much like you added jsprit-core.
	 * Just use jsprit-io. You can then use this:
	 */
	return new VrpXMLWriter(problem, solutions).write().toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(Example.class, args);
    }

}
