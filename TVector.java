package Algebra;
import java.text.DecimalFormat;

public class TVector implements IVector {

	protected double[] data;
	
	public TVector()
	{
		this.data=new double[1];
	}
	
	public TVector(int n)
	{
		this.data=new double[n];
	}
	
	public TVector(double[] a)
	{
		this.data=new double[a.length];
		for(int i=0;i<a.length;i++)
		{
			this.SetItem(i, a[i]);
		}
	}
	
	@Override
	public double GetItem(int i) {
		// TODO Auto-generated method stub
			return data[i];
	}

	@Override
	public void SetItem(int i, double value) {
		// TODO Auto-generated method stub
          data[i]=value;
	}

	@Override
	public int GetSize() {
		// TODO Auto-generated method stub
		 
		return data.length;
	}

	@Override
	public int GetHight() {
		// TODO Auto-generated method stub
		return data.length-1;
	}

	@Override
	public IVector Clone() {
		// TODO Auto-generated method stub	
		IVector vect=new TVector(this.GetSize());
		for(int i=0;i<this.GetSize();i++)
		{
			vect.SetItem(i, this.GetItem(i));
		}
		return vect;
	}

	@Override
	public void Resize(int n) {
		// TODO Auto-generated method stub
		 double[] a=new double[this.GetSize()];
		  a=this.data;
		
        this.data=new double[n];
        for(int i=0;i<n;i++)
        {
         if (i<a.length) 
  		   {
  		   this.SetItem(i,  a[i]);
  		   }
        }
        
	}

	@Override
	public IVector Add(IVector a) {
		// TODO Auto-generated method stub
		try {
		if (a.GetSize()!=this.GetSize()) throw new Exception("Несовместимые размерности");
		}
		catch(Exception ex)
		{
			Resize(a.GetSize());
		}
		IVector vect=new TVector(a.GetSize());
	   for(int i=0; i<a.GetSize();i++)
	   {
		  vect.SetItem(i,(a.GetItem(i)+this.GetItem(i)));
	   }
		return vect;
	}

	@Override
	public IVector Sub(IVector a) {
		// TODO Auto-generated method stub
		try {
			if (a.GetSize()!=this.GetSize()) throw new Exception("Несовместимые размерности");
			}
			catch(Exception ex)
			{
				Resize(a.GetSize());
			}
			IVector vect=new TVector(a.GetSize());
		   for(int i=0; i<this.GetSize();i++)
		   {
			  vect.SetItem(i,(this.GetItem(i)-a.GetItem(i)));
		   }
		return vect;
	}

	@Override
	public IVector Mult(double a) {
		// TODO Auto-generated method stub
		IVector vect=new TVector(this.GetSize());
		for(int i=0; i<this.GetSize();i++)
		   {
			  vect.SetItem(i,(this.GetItem(i)*a));
		   }
		return vect;
	}

	@Override
	public IVector Mult(IMatrix a) {
		// TODO Auto-generated method stub
		IVector vect=new TVector(a.GetColCount());
		for(int j=0;j<a.GetColCount();j++)
		   {
			 for(int i=0; i<a.GetRowCount();i++)
			 {
				 vect.SetItem(j, (vect.GetItem(j)+a.GetItem(i, j)*this.GetItem(i)));
			 }
		   }
		return vect;
	}

	@Override
	public double Mult(IVector a) {
		// TODO Auto-generated method stub
		try {
			if (a.GetSize()!=this.GetSize()) throw new Exception("Несовместимые размерности");
			}
			catch(Exception ex)
			{
				Resize(a.GetSize());
			}
        double b = 0;
		for(int i=0;i<this.GetSize();i++)
		{
			b+=a.GetItem(i)*this.GetItem(i);
		}
		return b;
	}

	@Override
	public IVector Cross(IVector a) {
		// TODO Auto-generated method stub
		IVector Cross=new TVector(this.GetSize());
		if (a.GetSize()==3 & this.GetSize()==3){
			Cross.SetItem(0, this.GetItem(1)*a.GetItem(2)-this.GetItem(2)*a.GetItem(1));
			Cross.SetItem(1, this.GetItem(2)*a.GetItem(0)-this.GetItem(0)*a.GetItem(2));
			Cross.SetItem(2, this.GetItem(0)*a.GetItem(1)-this.GetItem(1)*a.GetItem(0));
		}
		else System.out.println("Не подходящая размерность");
		return Cross;
	}

	@Override
	public double Length() {
		// TODO Auto-generated method stub
		double b=0;
		for(int i=0;i<this.GetSize();i++)
        {
       	 b+=this.GetItem(i)*this.GetItem(i);
        }
		return Math.pow(b, 0.5);
	}

	@Override
	public IVector Norm() {
		// TODO Auto-generated method stub
		IVector vect=new TVector(this.GetSize());
		for(int i=0; i<this.GetSize();i++)
		   {
			  vect.SetItem(i,(this.GetItem(i)/this.Length()));
		   }
		return vect;
	}
	
	@Override
	public void Print() {
		DecimalFormat df=new DecimalFormat("#.###############");
		System.out.print("( ");
		for (int i=0; i<GetSize(); i++) {
			System.out.print(df.format(GetItem(i))+" ");
		}
		System.out.println(")");
	}
	
	@Override
	public IVector Negativ() {
		IVector Neg = this.Clone();
		for (int i=0; i<Neg.GetSize(); i++) {
			if (Neg.GetItem(i)>0) {
				Neg.SetItem(i, Neg.GetItem(i)*(-1));
			}
		}
		return Neg;
	}

	@Override
	public IVector rotateByRodrigFormule(IVector e, double phi) {
		// TODO Auto-generated method stub
		IVector p1=new TVector(this.GetSize());
		IQuaternion Q=new TQuaternion(e,phi);
		IVector theta=new TVector(3);
		//p1=e.Mult(e.Mult(this)*(1-Math.cos(phi))).Add(e.Cross(this).Mult(Math.sin(phi))).Add(this.Mult(Math.cos(phi)));
		 theta.SetItem(0, 2*Q.GetItem(1)/Q.GetItem(0));
		 theta.SetItem(1, 2*Q.GetItem(2)/Q.GetItem(0));
		 theta.SetItem(2, 2*Q.GetItem(3)/Q.GetItem(0));
		    p1=this.Add(theta.Mult(1/(1+Math.pow(Math.tan(phi/2), 2))).Cross(this.Add(theta.Cross(this).Mult(1./2))));
		    return p1;
	}

	@Override
	public IVector rotateByQuaternion(IQuaternion Q) {
		// TODO Auto-generated method stub
		IVector p1=new TVector(3);
		IQuaternion t=new TQuaternion();
		t=Q.Multiply(this).Multiply(Q.inv());
		p1=t.GetVect();
	    return p1;
	}

	@Override
	public IVector toreturnKrilov(IQuaternion Q) {
		// TODO Auto-generated method stub
		//yaw - рысканье; pitch - тангаж; roll - крен
		double yaw=Math.atan((2*Q.GetItem(0)*Q.GetItem(2)-2*Q.GetItem(1)*Q.GetItem(3))/(2*Q.GetItem(0)*Q.GetItem(0)+2*Q.GetItem(1)*Q.GetItem(1)-1));
		double pitch=Math.asin((2*Q.GetItem(1)*Q.GetItem(2)+2*Q.GetItem(0)*Q.GetItem(3)));
		double roll=Math.atan((2*Q.GetItem(0)*Q.GetItem(1)-2*Q.GetItem(2)*Q.GetItem(3))/(2*Q.GetItem(0)*Q.GetItem(0)+2*Q.GetItem(2)*Q.GetItem(2)-1));
		double[] A= {yaw,pitch,roll};
		IVector p=new TVector(A);
		return p;
	}

	@Override
	public IMatrix inMatrix() {
		// TODO Auto-generated method stub
		try
		{
			if ((int)(Math.sqrt(this.GetSize()))!=Math.sqrt(this.GetSize())) throw new Exception("Несовместимые размерности");
		}
		catch (Exception ex)
		{
			this.Resize((int)Math.pow((int)Math.round(Math.sqrt(this.GetSize())),2));
		}
		IMatrix A=new TMatrix((int)Math.sqrt(this.GetSize()),(int)Math.sqrt(this.GetSize()));
		for(int j =0;j<(int)Math.sqrt(this.GetSize());j++)
				{
					for(int i =0;i<(int)Math.sqrt(this.GetSize());i++)
						{
				            A.SetItem(i, j, this.GetItem(A.GetColCount()*j+i));
						}
				}
		return A;
	}

}
