package insurance.model;

import java.util.List;

public class Derivative {
    private List<Insurance> obligations;

    public Derivative(List<Insurance> obligations) {
        this.obligations = obligations;
    }


    public Derivative(Derivative d){
        this.obligations = d.obligations;
    }
    public Derivative(){

    }

    public List<Insurance> getObligations() {
        return obligations;
    }

    public void setObligations(List<Insurance> obligations) {
        this.obligations = obligations;
    }

    public Derivative addObligation(Insurance oblig){
        obligations.add(oblig);
        return this;
    }

    public boolean delObligation(int id){
        for(int i = 0; i < obligations.size(); i++){
            if(obligations.get(i).getId() == id){
                obligations.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object der){
        if(der == this) return true;
        if(!(der instanceof Derivative)) return false;
        if(((Derivative)der).getObligations().size()!= this.obligations.size()) return false;
        for(int i = 0; i < obligations.size(); i++){
            if(!((obligations.get(i)).equals(((Derivative)der).getObligations().get(i))))
                return false;
        }
        return true;
    }


}
