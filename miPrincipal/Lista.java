package miPrincipal;

public class Lista<T> {
    private Nodo<T> cabeza;
    private int tamanio;

    //constructor vacio
    public Lista() {
        cabeza = null;
        tamanio= 0;

    }

    public boolean esVacia(){

        return (cabeza==null);
         
    }

    public void agregar(T valor){
        Nodo<T> nuevo = new Nodo<T>();
        nuevo.setValor(valor);
        if (this.esVacia()){
            cabeza = nuevo;
        }else{
            //agregar al final de la lista
            Nodo<T> aux = cabeza;
            while(aux.getSiguiente() != null){
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);


        }
        tamanio++;
    }

    public T getValor(int pos) throws PosicionIlegalException{
        if(pos >= 0 && pos<tamanio){
            T valor;
            if(pos==0){
                valor = cabeza.getValor();
                return valor;

            }
            else{
                Nodo<T> aux = cabeza;
                for(int i=0;i<=pos-1;i++){
                    aux = aux.getSiguiente();
                }
               valor= aux.getValor();
               return valor;

            }
            

        }else{
            throw new PosicionIlegalException();
        }

    }

    public void insertar(T valor, int pos) throws PosicionIlegalException{
        if (pos>=0 && pos<=tamanio){
            Nodo<T> nuevo = new Nodo<T>();
            nuevo.setValor(valor);
            if(pos==0){
                nuevo.setSiguiente(cabeza);
                cabeza=nuevo;
            }
            else{
                //insertar al final
                if(pos==tamanio){
                    Nodo<T> aux = cabeza;
                    while(aux.getSiguiente() != null){
                        aux = aux.getSiguiente();
                    }
                    aux.setSiguiente(nuevo);


                }else{
                    //inserta en medio
                    Nodo<T> aux = cabeza;
                    for(int i=0;i<=pos-2;i++){
                        aux = aux.getSiguiente();
                    }
                    Nodo<T> siguiente = aux.getSiguiente();
                    aux.setSiguiente(nuevo);
                    nuevo.setSiguiente(siguiente); 
                    


                }
            }
            tamanio++;


        }else{
            throw new PosicionIlegalException();
        }
    }

    public void remover(int pos) throws PosicionIlegalException{
        if(pos >= 0 && pos<tamanio){
            if(pos == 0){
                //el nodo a elimimar esta en la primera posicion
                cabeza = cabeza.getSiguiente();
                tamanio--;
            }else{
                Nodo<T> aux = cabeza;
                for(int i=0;i<=pos-2;i++){
                    aux = aux.getSiguiente();
                } 
                Nodo<T> prox = aux.getSiguiente();
                aux.setSiguiente(prox.getSiguiente());
                tamanio--;


            }
        }else{
            throw new PosicionIlegalException();
        }

      
        
    }
    public void limpiar(){
        cabeza=null;
        tamanio=0;
    }


    
    public int getTamanio() {
        return tamanio;
    }
   
    public Nodo<T> Buscar(T valor) {
        Nodo<T> aux = cabeza;
        while (aux != null) {
            if (aux.getValor().equals(valor)) {
                return aux; 
            }
            aux = aux.getSiguiente();
        }
        return null;
    }

    public boolean BuscarTOf(T valor) {
        return Buscar(valor) != null;
    }
}
