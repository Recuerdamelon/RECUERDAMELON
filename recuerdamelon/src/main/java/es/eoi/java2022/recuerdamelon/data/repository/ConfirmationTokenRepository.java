package es.eoi.java2022.recuerdamelon.data.repository;
import es.eoi.java2022.recuerdamelon.data.entity.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}