package br.com.dio.springwebmvcintermediario.repository;

import br.com.dio.springwebmvcintermediario.entity.SoldadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoldadoRepository extends JpaRepository<SoldadoEntity, Long> {

}