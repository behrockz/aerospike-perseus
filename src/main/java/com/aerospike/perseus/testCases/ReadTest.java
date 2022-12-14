package com.aerospike.perseus.testCases;

import com.aerospike.perseus.data.provider.DataProvider;
import com.aerospike.perseus.utilities.aerospike.AerospikeConfiguration;

public class ReadTest extends Test<Integer>{
    public ReadTest(AerospikeConfiguration conf, DataProvider<Integer> provider) {
        super(conf, provider);
    }

    @Override
    protected void execute(Integer key) {
        client.get(null, getKey(key));
    }

    public String getHeader(){
        return String.format( "Reads (%d)", threadCount.get());
    }
}
