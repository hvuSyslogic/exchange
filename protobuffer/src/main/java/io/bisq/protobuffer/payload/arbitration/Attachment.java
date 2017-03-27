package io.bisq.protobuffer.payload.arbitration;

import com.google.protobuf.ByteString;
import io.bisq.common.app.Version;
import io.bisq.generated.protobuffer.PB;
import io.bisq.protobuffer.payload.Payload;
import lombok.EqualsAndHashCode;
import org.bouncycastle.util.encoders.Hex;

@EqualsAndHashCode
public final class Attachment implements Payload {
    // That object is sent over the wire, so we need to take care of version compatibility.
    private static final long serialVersionUID = Version.P2P_NETWORK_VERSION;

    // Payload
    private final byte[] bytes;
    private final String fileName;

    public Attachment(String fileName, byte[] bytes) {
        this.fileName = fileName;
        this.bytes = bytes;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public String getFileName() {
        return fileName;
    }


    // Hex
    @Override
    public String toString() {
        return "Attachment{" +
                "description=" + fileName +
                ", data=" + Hex.toHexString(bytes) +
                '}';
    }

    @Override
    public PB.Attachment toProto() {
        return PB.Attachment.newBuilder().setBytes(ByteString.copyFrom(bytes))
                .setFileName(fileName).build();
    }
}