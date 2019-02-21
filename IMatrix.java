package Algebra;

public interface IMatrix {
public double GetItem(int i,int j) ;//������� ��������� �������� ��������
public void SetItem(int i,int j,double value);//��������� ��������� �������� ��������
public IVector inVector();
public int GetRowCount();//������� ��������� ���-�� �����
public int GetColCount();//������� ��������� ���-�� ��������
public int GetRowHigh();//������� ��������� ������� ��������� ������
public int GetColHigh();//������� ��������� ������� ���������� �������
public IMatrix Clone(); //������� �������� ����� �������
public void Resize(int n,int m);//��������� ��������� �����������
public IMatrix Add(IMatrix a);//������� �������� ������
public IMatrix Sub(IMatrix a);//������� ��������� ������
public IMatrix Mult(IMatrix a);//������� ��������� ������� �� �������
public IMatrix Mult(double a);//������� ��������� ������� �� �����
public IVector Mult(IVector a);//������� ��������� ������� �� ������
public double Det();//������� ���������� ������������
public IMatrix Inv();//������� ��������� �������
public IMatrix T();//������� ��������������� �������
public void SwapRows(int i,int j); //��������� ������������ �����
public void SwapCols(int i,int j); //��������� ������������ ��������
public IMatrix Invers();//������� ��������� �������� �������
public void Print();//����� ������� � �������
public IMatrix Holetckogo();// ����� ��������� ��� ������������ ������
public boolean Silvestr();// �������� ����������
public IMatrix InversSim(); // ����� ��������� ��� ���������� �������� ������� ��� ������������ ������
public IMatrix Negativ();  // ������� �������
}
