package Algebra;
import java.text.DecimalFormat;

public class TQuaternion implements IQuaternion {
	//скал€рна€ часть кватерниона
    private double q0;
    private IVector Q;
	
    public TQuaternion(double q0,double q1,double q2,double q3)
    {
    	this.q0=q0;
    	Q=new TVector(3);
    	Q.SetItem(0, q1);
    	Q.SetItem(1, q2);
    	Q.SetItem(2, q3);
    }
    
    public TQuaternion(IVector e,double phi)
    {
    	try {
			if (e.GetSize()!=3)  throw new Exception("Ќеверно задан вектор оси поворота");
			this.q0=Math.cos(phi/2);
	    	Q=e.Clone();
	    	Q=Q.Norm();
	    	Q=Q.Mult(Math.sin(phi/2));
			}
			catch(Exception ex)
			{
				ex.getMessage();
			}
    }
    
    public TQuaternion(double yaw,double pitch,double roll)//задаетс€ в градусной мере 
    //yaw - рысканье; pitch - тангаж; roll - крен
    {
    	this.q0=Math.cos(roll/2)*Math.cos(yaw/2)*Math.cos(pitch/2)-Math.sin(roll/2)*Math.sin(yaw/2)*Math.sin(pitch/2);
    	Q=new TVector(3);
    	Q.SetItem(0, Math.sin(roll/2)*Math.cos(yaw/2)*Math.cos(pitch/2)+Math.cos(roll/2)*Math.sin(yaw/2)*Math.sin(pitch/2));
    	Q.SetItem(1, Math.cos(roll/2)*Math.sin(yaw/2)*Math.cos(pitch/2)+Math.sin(roll/2)*Math.cos(yaw/2)*Math.sin(pitch/2));
    	Q.SetItem(2, Math.cos(roll/2)*Math.cos(yaw/2)*Math.sin(pitch/2)-Math.sin(roll/2)*Math.sin(yaw/2)*Math.cos(pitch/2));
    }
    
    public TQuaternion()
    {
    	this.q0=0;
    	Q=new TVector(3);
    }
    
    @Override
	public double GetItem(int i) {
		// TODO Auto-generated method stub 
			try {
				if ((i<0) || (i>3))  throw new Exception("Ќеверный индекс элемента кватерниона");
				}
				catch(Exception ex)
				{
					ex.getMessage();
				}
			if (i==0) { return q0; }
			else return Q.GetItem(i-1);
	}

	@Override
	public IVector GetVect() {
		// TODO Auto-generated method stub
		return Q.Clone();	
	}

	@Override
	public IQuaternion Clone() {
		// TODO Auto-generated method stub
		IQuaternion Q=new TQuaternion();
		((TQuaternion)Q).q0=this.q0;
		((TQuaternion)Q).Q=this.Q;
		return Q;
	}


	@Override
	public IQuaternion Negative() {
		// TODO Auto-generated method stub
		IQuaternion Q=this.Clone();
		if (Q.GetItem(0) > 0)
			((TQuaternion)Q).q0=Q.GetItem(0)*(-1);
		for(int i=0;i<3;i++)
		((TQuaternion)Q).Q=Q.GetVect().Negativ();
		return Q;
	}

	@Override
	public IQuaternion Add(IQuaternion a) {
		// TODO Auto-generated method stub
		IQuaternion Q= new TQuaternion();
		((TQuaternion)Q).q0=a.GetItem(0)+this.GetItem(0);
		((TQuaternion)Q).Q=a.GetVect().Add(this.GetVect());
		return Q;
	}

	@Override
	public IQuaternion Substract(IQuaternion a) {
		// TODO Auto-generated method stub
		IQuaternion Q= new TQuaternion();
		((TQuaternion)Q).q0=this.GetItem(0)-a.GetItem(0);
		((TQuaternion)Q).Q=this.GetVect().Sub(a.GetVect());
		return Q;
	}

	@Override
	public IQuaternion Multiply(IQuaternion a) {
		// TODO Auto-generated method stub
		IQuaternion C=new TQuaternion();
		((TQuaternion)C).q0=this.GetItem(0)*a.GetItem(0)-this.GetVect().Mult(a.GetVect());
		((TQuaternion)C).Q=this.GetVect().Cross(a.GetVect()).Add(this.GetVect().Mult(a.GetItem(0))).Add(a.GetVect().Mult(this.GetItem(0)));	
		return C;
	}

	@Override
	public IQuaternion Multiply(double a) {
		// TODO Auto-generated method stub
		IQuaternion Q=this.Clone();
		((TQuaternion)Q).q0=((TQuaternion)Q).q0*a;
		((TQuaternion)Q).Q=((TQuaternion)Q).Q.Mult(a);
		return Q;
	}


	@Override
	public IQuaternion Multiply(IVector a) {
		// TODO Auto-generated method stub
		IQuaternion Q=this.Clone();
		IQuaternion Q1=new TQuaternion(0,a.GetItem(0),a.GetItem(1),a.GetItem(2));
		Q=Q.Multiply(Q1);
		return Q;
	}

	@Override
	public IQuaternion norm() {
		// TODO Auto-generated method stub
		IQuaternion Q= new TQuaternion();
		((TQuaternion)Q).q0=this.GetItem(0)*1/this.Length();
		((TQuaternion)Q).Q=this.GetVect().Mult(1/this.Length());
		return Q;
	}

	@Override
	public IQuaternion conj() {
		// TODO Auto-generated method stub
		IQuaternion Q= new TQuaternion();
		((TQuaternion)Q).q0=this.GetItem(0);
		((TQuaternion)Q).Q=this.GetVect().Mult(-1);
		return Q;
	}

	@Override
	public IQuaternion inv() {
		// TODO Auto-generated method stub
		return this.conj().Multiply(1/(this.Length() * this.Length()));
	}

	@Override
	public IMatrix toRotateMatrix() {
		// TODO Auto-generated method stub
		IMatrix A=new TMatrix(3,3);
		IQuaternion Q=this.norm();
		A.SetItem(0, 0, Q.GetItem(0)*Q.GetItem(0)+Q.GetItem(1)*Q.GetItem(1)-Q.GetItem(2)*Q.GetItem(2)-Q.GetItem(3)*Q.GetItem(3));
		A.SetItem(1, 1, Q.GetItem(0)*Q.GetItem(0)-Q.GetItem(1)*Q.GetItem(1)+Q.GetItem(2)*Q.GetItem(2)-Q.GetItem(3)*Q.GetItem(3));
		A.SetItem(2, 2, Q.GetItem(0)*Q.GetItem(0)-Q.GetItem(1)*Q.GetItem(1)-Q.GetItem(2)*Q.GetItem(2)+Q.GetItem(3)*Q.GetItem(3));
		
		
		A.SetItem(0, 1, 2*(Q.GetItem(1)*Q.GetItem(2)-Q.GetItem(0)*Q.GetItem(3)));
		A.SetItem(0, 2, 2*(Q.GetItem(0)*Q.GetItem(2)+Q.GetItem(1)*Q.GetItem(3)));
		
		A.SetItem(1, 0, 2*(Q.GetItem(1)*Q.GetItem(2)+Q.GetItem(0)*Q.GetItem(3)));
		A.SetItem(2, 0, 2*(Q.GetItem(1)*Q.GetItem(3)-Q.GetItem(0)*Q.GetItem(2)));
		
		A.SetItem(1, 2, 2*(Q.GetItem(2)*Q.GetItem(3)-Q.GetItem(0)*Q.GetItem(1)));
		A.SetItem(2, 1, 2*(Q.GetItem(0)*Q.GetItem(1)+Q.GetItem(2)*Q.GetItem(3)));
		return A;
	}

	@Override
	public void Print() {
		// TODO Auto-generated method stub
		DecimalFormat df=new DecimalFormat("#.###############");
		System.out.print("( ");
		for (int i=0; i<4; i++) {
			System.out.print(df.format(this.GetItem(i))+" ");
		}
		System.out.println(")");
	}

	@Override
	public double Length() {
		// TODO Auto-generated method stub
		double a=0;
		for(int i=0;i<4;i++)
		a+=this.GetItem(i)*this.GetItem(i);
		return Math.pow(a, 0.5);
	}

	@Override
	public IQuaternion toReturnQ(IMatrix A) {
		// TODO Auto-generated method stub
		try {
		double q0=Math.pow((1+A.GetItem(0, 0)+A.GetItem(1, 1)+A.GetItem(2, 2)),0.5)/2;
		double q1=(A.GetItem(2, 1)-A.GetItem(1, 2))/(4*q0);
		double q2=(A.GetItem(0, 2)-A.GetItem(2, 0))/(4*q0);
		double q3=(A.GetItem(1, 0)-A.GetItem(0, 1))/(4*q0);
		IQuaternion Q= new TQuaternion(q0,q1,q2,q3);
		return Q;
		}
		catch (Exception ex)
		{
			System.out.println("не восстанавливает кватерниона с Ћ0=0");
		}
		return null;
	}

}
