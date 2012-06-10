package packt;

import javax.ejb.Stateless;

@Stateless()
public class SalutationBean {
    public String getFormalSalutation(String name) {
        return "Dear " + name;
    }

    public String getInformalSalutation(String name) {
        return "Hi " + name;
    }
}
