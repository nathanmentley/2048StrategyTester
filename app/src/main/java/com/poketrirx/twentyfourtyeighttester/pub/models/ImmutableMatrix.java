package com.poketrirx.twentyfourtyeighttester.pub.models;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class ImmutableMatrix<T>
{
    private List<List<T>> matrix;

    public ImmutableMatrix(List<List<T>> matrix)
    {
        this.matrix = new ArrayList<List<T>>(matrix.size());

        for (List<T> items : matrix) {
            this.matrix.add(new ArrayList<T>(items));
        }
    }

    public T get(int x, int y)
    {
        return matrix.get(x).get(y);
    }

    public List<List<T>> toMutable() {
        List<List<T>> ret = new ArrayList<List<T>>(matrix.size());

        for (List<T> items : this.matrix) {
            ret.add(new ArrayList<T>(items));
        }

        return ret;
    }

    public ImmutableMatrix<T> rotateRight() {
        List<List<T>> newMatrix = toMutable();

        int N = newMatrix.size();

        for (int i = 0; i < N / 2; i++)
        for (int j = i; j < N - i - 1; j++) {
            T temp = newMatrix.get(i).get(j);

            newMatrix.get(i).set(j, newMatrix.get(N - 1 - j).get(i));
    
            newMatrix.get(N - 1 - j).set(i, newMatrix.get(N - 1 - i).get(N - 1 - j));

            newMatrix.get(N - 1 - i).set(N - 1 - j, newMatrix.get(j).get(N - 1 - i));

            newMatrix.get(j).set(N - 1 - i, temp);
        }

        return new ImmutableMatrix<T>(newMatrix);
    }
}