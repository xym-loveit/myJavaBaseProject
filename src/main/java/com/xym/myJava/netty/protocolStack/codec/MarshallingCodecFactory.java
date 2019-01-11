package com.xym.myJava.netty.protocolStack.codec;

import org.jboss.marshalling.*;

import java.io.IOException;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-01-10 15:23
 */
public class MarshallingCodecFactory {

    protected static Marshaller buildMarshalling() throws IOException {
        final MarshallerFactory marshallerFactory = Marshalling
                .getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        Marshaller marshaller = marshallerFactory
                .createMarshaller(configuration);
        return marshaller;
    }

    protected static Unmarshaller buildUnMarshalling() throws IOException {
        final MarshallerFactory marshallerFactory = Marshalling
                .getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        final Unmarshaller unmarshaller = marshallerFactory
                .createUnmarshaller(configuration);
        return unmarshaller;
    }

}
