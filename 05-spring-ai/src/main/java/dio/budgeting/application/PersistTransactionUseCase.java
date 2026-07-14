package dio.budgeting.application;

import dio.budgeting.application.input.PersistTransactionInput;
import dio.budgeting.application.output.TransactionOutput;
import dio.budgeting.domain.Transaction;
import dio.budgeting.service.TransactionService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
public class PersistTransactionUseCase {

    private final TransactionService transactionService;

    public PersistTransactionUseCase(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Tool(name = "persist-transaction", description = "Persiste uma nova transação financeira")
    public TransactionOutput execute(PersistTransactionInput input) {

        var transactionToSave = new Transaction(input.description(), input.amount(), input.category());

        var savedTransaction = transactionService.processarTransacao(transactionToSave);

        return TransactionOutput.from(savedTransaction);
    }
}