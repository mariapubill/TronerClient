package model;

import java.io.Serializable;


    /**
     * creem la classe servidorClient, que conte el fortmat dels itmes del client.
     * client --> servidor
     */
    public class Petition implements Serializable {
        private int key;
        private static final long serialVersionUID = 5940092344965987487L;

        /**
         * construictor amb paramnetres
         * @param key: int que ens indica el codi corresponent a la tecla premuda pel client
         */
        public Petition(int key){
            this.key = key;

        }


    }

