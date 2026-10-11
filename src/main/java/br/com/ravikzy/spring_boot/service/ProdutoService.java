package br.com.ravikzy.spring_boot.service;

import br.com.ravikzy.spring_boot.database.model.ProdutoEntity;
import br.com.ravikzy.spring_boot.dto.ProdutoDto;
import br.com.ravikzy.spring_boot.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {
    private static final List<ProdutoEntity> PRODUTOS = new ArrayList<>();

    static  {
            PRODUTOS.add(ProdutoEntity.builder()
                    .id(1)
                    .nome("Notebook")
                    .preco(new BigDecimal(500))
                    .quantidade(10)
            .build());

            PRODUTOS.add(ProdutoEntity.builder()
                    .id(2)
                    .nome("Smartphone")
                    .preco(new BigDecimal("1500.00"))
                    .quantidade(25)
                    .build());

            PRODUTOS.add(ProdutoEntity.builder()
                    .id(3)
                    .nome("Monitor Gamer")
                    .preco(new BigDecimal("1200.50"))
                    .quantidade(8)
            .build());

            PRODUTOS.add(ProdutoEntity.builder()
                .id(4)
                .nome("Teclado Mecânico")
                .preco(new BigDecimal("350.00"))
                .quantidade(15)
            .build());

            PRODUTOS.add(ProdutoEntity.builder()
                .id(5)
                .nome("Mouse Sem Fio")
                .preco(new BigDecimal("180.00"))
                .quantidade(40)
            .build());

    }

    public List<ProdutoEntity> findAll() {
        return new ArrayList<>(PRODUTOS);
    }


    public ProdutoEntity createProduto(ProdutoDto produtoDto){

        Integer identificador = PRODUTOS.stream()
                .mapToInt(ProdutoEntity::getId)
                .max()
                .orElse(0) + 1;

        ProdutoEntity novoProduto = ProdutoEntity.builder()
                 .id(identificador)
                .nome(produtoDto.getNome())
                .preco(produtoDto.getPreco())
                .quantidade(produtoDto.getQuantidade())
                .build();

        PRODUTOS.add(novoProduto);

        return novoProduto;
    }

    public ProdutoEntity atualizarProduto(ProdutoDto produtoDto, Integer id) throws NotFoundException {
         ProdutoEntity produto = PRODUTOS.stream()
                .filter(p -> p.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new NotFoundException("Produto não encontrado"));

         produto.setNome(produtoDto.getNome());
         produto.setPreco(produtoDto.getPreco());
         produto.setQuantidade(produtoDto.getQuantidade());

         return produto;
    }

    public void deletarProduto(Integer id) {
        PRODUTOS.removeIf(p -> p.getId().equals(id));
    }

}


