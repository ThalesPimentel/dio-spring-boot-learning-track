package dio.budgeting.service;

import dio.budgeting.domain.Transaction;
import dio.budgeting.domain.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction processarTransacao(Transaction transacao) {

        //O valor deve ser estritamente positivo
        if (transacao.getAmount() <= 0) {
            throw new IllegalArgumentException("Erro de Validação: O valor da transação deve ser maior que zero.");
        }

        //A categoria é obrigatória
        if (transacao.getCategory() == null) {
            throw new IllegalArgumentException("Erro de Validação: A categoria é obrigatória e não pode estar vazia.");
        }

        //Se passou pelas validações, então salva no banco
        return transactionRepository.save(transacao);
    }
}