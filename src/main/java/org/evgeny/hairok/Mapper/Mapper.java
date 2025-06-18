package org.evgeny.hairok.Mapper;

public interface Mapper<F,T> {
    T map(F f);
}
