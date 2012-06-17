package packt.ws;

import packt.TimeOfDay;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;


/*
*
* To get the service definition : http://localhost:8080/LearnEJB2/TimeJaxWs?wsdl
*
* */

@WebService
public class TimeJaxWs {

    @EJB
    private TimeOfDay timeOfDay;

    @WebMethod(operationName = "timeOfDay")
    public String timeOfDay() {
        return timeOfDay.timeOfDay();
    }
}
