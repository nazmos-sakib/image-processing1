
public class DFT_2{

Complex[][]f; //Basisfunktionen
Complex[][]g; //Basisfunktionen

int N=1;
int M=1;

public DFT_2(int N, int M){

  if(N>0 && M> 0){
  this.N=N;
  this.M=M;

  f = new Complex[N][N];
  g = new Complex[M][M];

    calculateBasisFunctions();
  }

}

//komplexe Skalarprodukt im N,M-Dim-komplexen Raum

public Complex skalar(Complex[][] x, Complex[][] y){
Complex z=new Complex(0,0);

  for(int n=0; n<N;n++){
    for(int m=0; m<M;m++){

    z= Complex.add(z, Complex.mul(x[n][m], Complex.conj(y[n][m])));

    }
  }
  return z;
}


private void calculateBasisFunctions(){

  for(int n=0;n<N;n++){
          for(int k=0;k<N;k++){
          double w=Math.PI* 2.0 * (double)n*(double)k /(double)N;

          Complex z=new Complex(Math.cos(w) , Math.sin(w)); //exp(iw) mit Euler'scher Identität
          Complex norm=new Complex(1.0 / Math.sqrt(N),0);
          z=Complex.mul(z,norm);   //Normierung
          f[n][k]=z; //Normierte Basisfunktionen
          }
  }

  for(int m=0;m<M;m++){
          for(int l=0;l<M;l++){
          double w=Math.PI* 2.0 * (double)m*(double)l /(double)M;

          Complex z=new Complex(Math.cos(w) , Math.sin(w)); //exp(iw) mit Euler'scher Identität
          Complex norm=new Complex(1.0 / Math.sqrt(M),0);
          z=Complex.mul(z,norm);   //Normierung
          g[m][l]=z; //Normierte Basisfunktionen
          }
  }

}

public Complex[][] transform(Complex[][] x){ //DFT2

Complex[][] c=new Complex[N][M];


  for(int k=0;k<N; k++){
    for(int l=0;l<M; l++){

    Complex z=new Complex(0,0);

            for(int n=0; n<N;n++){
                    for(int m=0; m<M;m++){

                    z= Complex.add(z, Complex.mul(x[n][m], Complex.conj(Complex.mul(f[n][k],g[m][l]))));

                    }
            }
      c[k][l]=z;

     }
  }

return c;
}


public Complex[][] invtrans(Complex[][] c){ // Inverse DFT2
Complex[][] x= new Complex[N][M];
  for(int n=0;n<N;n++){
          for(int m=0;m<M;m++){
                  Complex z=new Complex(0.0,0.0);
    
            for(int k=0;k<N; k++){
                    for(int l=0;l<M; l++){

                        z=Complex.add(z,Complex.mul(c[k][l],Complex.mul(f[n][k],g[m][l])));

            }
            }
  x[n][m]=z;
  }
  }
  return x;
}



}
