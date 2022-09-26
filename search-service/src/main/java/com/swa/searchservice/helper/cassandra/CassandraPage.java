package com.swa.searchservice.helper.cassandra;

import lombok.Data;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;

import java.util.List;

@Data
public class CassandraPage<T> {

    private final Integer noOfElements;
    private final List<T> content;
    private final String pagingState;
    private final Boolean hasNext;

    public CassandraPage(final Slice<T> slice) {
        this.content = slice.getContent();
        this.noOfElements = content.size();
        this.hasNext = slice.hasNext();
        this.pagingState = getPagingState(slice);
    }

    private static <T> String getPagingState(final Slice<T> slice) {
        if (slice.hasNext()) {
            CassandraPageRequest next = (CassandraPageRequest) slice.nextPageable();

//            CassandraPageRequest cassandraPageRequest = (CassandraPageRequest) slice.nextPageable();
//            ByteBuffer byteBuffer = Objects.requireNonNull(cassandraPageRequest.getPagingState());
//            return byteBuffer.toString();
            return next.getPagingState().toString();
        } else {
            return null;
        }
    }
//
//    public Optional<String> getPagingState() {
//        return Optional.ofNullable(pagingState);
//    }
}