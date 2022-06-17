package tech.dimitar.service.grpc.demo.grpc;

import com.example.grpc.server.grpcserver.PingPongServiceGrpc;
import com.example.grpc.server.grpcserver.PingRequest;
import com.example.grpc.server.grpcserver.PongResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

@GrpcService
public class PingPongServiceImpl extends PingPongServiceGrpc.PingPongServiceImplBase {

    //@Secured("email")
    @PreAuthorize("hasAuthority('User')")
    @Override
    public void ping(
            PingRequest request, StreamObserver<PongResponse> responseObserver) {
        String pong = new StringBuilder()
                .append("pong response")
                .toString();
        PongResponse response = PongResponse.newBuilder()
                .setPong(pong)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}