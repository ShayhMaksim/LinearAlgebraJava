package Algebra;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TMatrix implements IMatrix {

	double[][] data;
	
	public TMatrix()
	{
		this.data=new double[1][1];
	}
	
	public TMatrix(int n,int m)
	{
		this.data=new double[n][m];
	}
	
	public TMatrix(double[][] A)
	{
		this.data=new double[A.length][A[0].length];
		for (int i=0;i<A.length;i++)
		{
			for(int j=0;j<A[0].length;j++)
			{
				this.SetItem(i, j, A[i][j]);
			}
		}
	}
	
	public IVector inVector()
	{
		IVector vect=new TVector(this.GetRowCount()*this.GetColCount());
		for(int j=0;j<this.GetRowCount();j++)
		{
			for(int i=0;i<this.GetRowCount();i++)
			{
				vect.SetItem(this.GetColCount()*j+i, this.GetItem(i, j));
			}
		}
		return vect;
	}
	
	public  static IMatrix E(int k) {   //////////единичная матрица
		IMatrix E = new TMatrix(k,k);
		for (int i=0; i<k;i++) {
			for (int j=0; j<k; j++) {
				if (i != j) E.SetItem(i, j, 0);
				else E.SetItem(i, j, 1);
			}	
		}
		return E;
	}
	@Override
	public double GetItem(int i, int j) {
		// TODO Auto-generated method stub
		return data[i][j];
	}

	@Override
	public void SetItem(int i, int j, double value) {
		// TODO Auto-generated method stub
         data[i][j]=value;
	}

	@Override
	public int GetRowCount() {
		// TODO Auto-generated method stub		
		return data.length;
	}

	@Override
	public int GetColCount() {
		// TODO Auto-generated method stub
		if (data.length>0) {
			return data[0].length;
		}
		else return 0;
	}
	
	@Override
	public int GetColHigh()
	{
		return GetColCount()-1;
	}

	@Override
	public int GetRowHigh() {
		// TODO Auto-generated method stub
		return GetRowCount()-1;
	}

	@Override
	public IMatrix Clone()  {
		// TODO Auto-generated method stub
		IMatrix matrix=new TMatrix(this.GetRowCount(),this.GetColCount());
		for(int i=0;i<this.GetRowCount();i++)
		{
			for(int j=0;j<this.GetColCount();j++)
			{
				matrix.SetItem(i, j, this.GetItem(i, j));
			}
		}
			return matrix;
	}

	@Override
	public void Resize(int n, int m) {
		// TODO Auto-generated method stub
	   double[][] a=this.data;
       this.data=new double[n][m];
       for(int i=0;i<n;i++)
       {
    	   for(int j=0;j<m;j++)
    	   {
    		   if (i<a.length && j<a[0].length) 
    		   {
    		   this.SetItem(i, j, a[i][j]);
    		   }	   
    		   else this.SetItem(i, j, 0);
    	   }
       }
	}

	@Override
	public IMatrix Add(IMatrix a) {
		// TODO Auto-generated method stub
		try {
			if (a.GetColCount()!=this.GetColCount()) throw new Exception("Несовместимые размерности");
			}
			catch(Exception ex)
			{
				Resize(a.GetRowHigh(),a.GetColCount());
			}
		IMatrix matrix=new TMatrix(this.GetRowCount(),this.GetColCount());
		for(int i=0;i<this.GetRowCount();i++)
		{
			for(int j=0;j<this.GetColCount();j++)
			{
				
					matrix.SetItem(i, j, (a.GetItem(i, j)+this.GetItem(i, j)));
				
			}
		}
		return matrix;
	}

	@Override
	public IMatrix Sub(IMatrix a) {
		// TODO Auto-generated method stub
		try {
			if (a.GetColCount()!=this.GetColCount()) throw new Exception("Несовместимые размерности");
			}
			catch(Exception ex)
			{
				Resize(a.GetRowHigh(),a.GetColCount());
			}
		IMatrix matrix=new TMatrix(this.GetRowCount(),this.GetColCount());
		for(int i=0;i<this.GetRowCount();i++)
		{
			for(int j=0;j<this.GetColCount();j++)
			{
					matrix.SetItem(i, j, (this.GetItem(i, j)-a.GetItem(i, j)));
			}
		}
		return matrix;
	}

	@Override
	public IMatrix Mult(IMatrix a) {
		// TODO Auto-generated method stub
		try {
			if (a.GetColCount()!=this.GetColCount()) throw new Exception("Несовместимые размерности");
			}
			catch(Exception ex)
			{
				Resize(a.GetRowHigh(),a.GetColHigh());
			}
		IMatrix matrix=new TMatrix(this.GetRowCount(),this.GetColCount());
		for(int i=0;i<this.GetRowCount();i++)
		{
			for(int j=0;j<this.GetColCount();j++)
			{
				matrix.SetItem(i, j, 0);
				for(int k=0;k<a.GetColCount();k++)
				{
					matrix.SetItem(i, j, (matrix.GetItem(i, j)+a.GetItem(k, j)*this.GetItem(i, k)));
				}
			}
		}
		return matrix;
	}

	@Override
	public IMatrix Mult(double a) {
		// TODO Auto-generated method stub
		IMatrix matrix=new TMatrix(this.GetRowCount(),this.GetColCount());
		for(int i=0;i<this.GetRowCount();i++)
		{
			for(int j=0;j<this.GetColCount();j++)
			{
					matrix.SetItem(i, j, (this.GetItem(i, j)*a));
			}
		}
		return matrix;
	
	}

	@Override
	public IVector Mult(IVector a) {
		// TODO Auto-generated method stub
		try {
			if (a.GetSize()!=this.GetRowCount()) throw new Exception("Несовместимые размерности");
			}
			catch(Exception ex)
			{
			
			}
		IVector vect=new TVector(this.GetColCount());
		for(int i=0; i<this.GetColCount();i++)
		   {
			 for(int j=0;j<this.GetRowCount();j++)
			 {
				 vect.SetItem(i, (vect.GetItem(i)+a.GetItem(j)*this.GetItem(i, j)));
			 }
		   }
		return vect;
		
	}

	@Override
	public double Det() {
		// TODO Auto-generated method stub
		//клонируем матрицу
		int count;
		double result=1;
		IMatrix mt1=new TMatrix(this.GetRowCount(),this.GetColCount());
		mt1=this.Clone();
		IMatrix mt2=new TMatrix(this.GetRowCount(),this.GetColCount());
		mt2=this.Clone();
		/*матрицы mt1 и mt2 необходимы,чтобы не испортить получаемую матрицу "this",которая может быть использована в других областях программы
		строим треугольную матрицу*/
		for(int i=0;i<this.GetColCount();i++)
		{
			if (mt1.GetItem(i, i)==0 && i<this.GetRowCount()-1)
			{
				count=i;
				do {
					mt1.GetItem(count, 0);
					count++;
					if (mt1.GetItem(i, i)==0 && i<this.GetColCount()-1) 
					{ 
					count=i; 
					do { 
					mt1.GetItem(count, 0); 
					count++; 
					}while (mt1.GetItem(count, 0)==0); 
					mt1.SwapCols(i, count); 
					result=-result; 
					} 
				}while (mt1.GetItem(count, 0)==0);
				mt1.SwapRows(i, count);
				result=-result;			
			}
			/*копирование рабочей матрицы в статическую мт2,необходимо для корректного расчета коэффициентов умножения строк перед вычитанием*/
			for(int x=0;x<this.GetRowCount();x++)
			{
				for(int y=0;y<this.GetColCount();y++)
				{
					mt2.SetItem(x, y, mt1.GetItem(x, y));
				}
			}
			//зануление i-того столбца
			for(int j=i+1;j<this.GetRowCount();j++)
			{
				for(int k=0;k<this.GetColCount();k++)
				{
					double tmp=mt1.GetItem(j, k)-((mt1.GetItem(i, k)*mt2.GetItem(j, i))/mt1.GetItem(i, i));
					mt1.SetItem(j, k, tmp);
				}
			}
			//вычисление определителя	
		}
		for(int x=0;x<this.GetRowCount();x++)
		{
			result=result*mt1.GetItem(x, x);
		}
		return result;
	}

	@Override
	public IMatrix Inv() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMatrix T() {
		// TODO Auto-generated method stub
		IMatrix mt2=new TMatrix(this.GetColCount(),this.GetRowCount());
		for(int i=0;i<this.GetRowCount();i++)
		{
			for(int j=0;j<this.GetColCount();j++)
			{
				mt2.SetItem(j, i, this.GetItem(i, j));
			}
		}
		return mt2;
	}

	@Override
	public void SwapRows(int i, int j) {
		// TODO Auto-generated method stub
		double vrem;
		
			for(int d=0;d<this.GetColCount();d++)
			{
				vrem = this.GetItem(i, d);
				this.SetItem(i, d, this.GetItem(j, d));
				this.SetItem(j, d, vrem);
			}
	}
	
	@Override
	public void SwapCols(int i, int j) {
		// TODO Auto-generated method stub
		double vrem;;
		
			for(int d=0;d<this.GetRowCount();d++)
			{
				vrem = this.GetItem(d, i);
				this.SetItem(d, i, this.GetItem(d, j));
				this.SetItem(d, j, vrem);
			}
	}
	
	@Override
	public IMatrix Invers() {
		double const1 = 0.00001;
		if (this.GetRowCount()!=this.GetColCount()) {
			System.out.println("не квадратная матрица");
			return null;
		}
		IMatrix X = new TMatrix(this.GetRowCount(),this.GetColCount());
		IMatrix A = this.Clone();
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		for (int i=0; i<X.GetRowCount(); i++) {
			if (A.GetItem(i, i)<const1 && A.GetItem(i, i)>-const1) {
				int k=1;
				double max = A.GetItem(i, i);
				int numRows = i;
				for (k = 1; i+k<A.GetRowCount(); k++) {
					if (!(A.GetItem(i+k, i)<const1 && A.GetItem(i+k, i)>-const1) && Math.abs(A.GetItem(i+k, i))>Math.abs(max)) {
						max = A.GetItem(i+k, i);
						numRows = i+k;
					}	
				}
				A.SwapRows(i, numRows);	
				list1.add(i);
				list2.add(numRows);
				}
			}
					
		for (int i=0; i<X.GetRowCount(); i++) {
			X.SetItem(i, i, 1);	
		}

		for (int i=0; i<X.GetRowCount(); i++) {
			IMatrix r = A.Clone();
			for (int j=0; j<X.GetColCount(); j++) {
				X.SetItem(i, j, X.GetItem(i, j)/r.GetItem(i,i));
				A.SetItem(i, j, A.GetItem(i, j)/r.GetItem(i,i));
				
			}
			
			r = A.Clone();
			for (int j=0; j<X.GetColCount(); j++) {
				for (int k=0; k<X.GetColCount(); k++) {
					if (k!=i) {
						A.SetItem(k, j, A.GetItem(k, j)-r.GetItem(k, i)*A.GetItem(i, j));
						X.SetItem(k, j, X.GetItem(k, j)-r.GetItem(k, i)*X.GetItem(i, j));
					}
					
				}
			}
		}

		
		
		if (list1.size()>0) {
			for (int i=0; i<list1.size(); i++) {
				X.SwapCols(list1.get(list1.size()-1-i), list2.get(list2.size()-1-i));
			}
		}

		return X;
	}
	
	@Override
	public void Print() {
		DecimalFormat df=new DecimalFormat("#.###############");
		for (int i=0; i<GetRowCount(); i++) {
			for (int j=0; j<GetColCount(); j++) {
				System.out.print(df.format(GetItem(i,j))+" ");
			}	
			System.out.println("");
		}
	}
	
	@Override
	public IMatrix Holetckogo()
	{	
		if (this.Silvestr()==false) {
			System.out.println("не положительна определена");
			return null;
		}
		
		IMatrix L = this.Clone();
		for (int i=0; i<L.GetRowCount();i++) {
			for (int j=0; j<L.GetColCount(); j++) {
				if (i<j) L.SetItem(i, j, 0);
				if (i>j) {
					double sub = L.GetItem(i, j);
					for (int k=0; k<j;k++) {
						sub-=L.GetItem(i, k)*L.GetItem(j, k);
					}
					L.SetItem(i, j, 1/L.GetItem(j, j)*sub);
				}
				if (i==j) {
					double sum=this.GetItem(i, i);
					for (int k=0; k<i;k++) {
						sum-=L.GetItem(i, k)*L.GetItem(i, k);
					}
					L.SetItem(i, i, Math.pow(sum,0.5));
				}
			}
			
		}
		return L;
	}
	
	@Override
	public boolean Silvestr()
	{
		IMatrix L = this.Clone();
		boolean b = true;
		for(int i=this.GetRowCount();i>0;i--)
		{
			L.Resize(i, i);
			if (L.Det()>0) {
				b=true;
			} else b=false;
		}
		return b;		
	}
	
	@Override
	public IMatrix InversSim() {
		IMatrix L = this.Holetckogo();
		L.Print();
		System.out.println("L");
		IMatrix X = new TMatrix(this.GetRowCount(),this.GetColCount());
		for (int i=this.GetRowCount()-1; i>=0;i--) {
			for (int j=this.GetRowCount()-1; j>=0; j--) {
				if (i==j) {
					double sum=0;
					for (int k=i+1; k<this.GetRowCount();k++) {
						sum+=L.GetItem(k, i)*X.GetItem(k, i);
					}
					X.SetItem(i, i, 1/L.GetItem(i, i)*(1/L.GetItem(i, i)-sum));
				}
				if (i!=j) {
					double sum=0;
					for (int k=j+1; k<this.GetRowCount();k++) {
						sum+=L.GetItem(k, j)*X.GetItem(i, k);
					}
					X.SetItem(i, j, -1/L.GetItem(j, j)*sum);
				}
				if (j>i) {
					X.SetItem(i, j, X.GetItem(j, i));
				}
			}
			
		}
		return X;
	}
	
	public IMatrix Negativ() {
		IMatrix Neg = this.Clone();
		for (int i=0; i<Neg.GetRowCount(); i++) {
			for (int j=0; j<Neg.GetColCount(); j++) {
				if (Neg.GetItem(i,j)>0) {
					Neg.SetItem(i,j, Neg.GetItem(i,j)*(-1));
				}
			}
		}
		return Neg;
	}

}
