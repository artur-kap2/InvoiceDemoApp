
CREATE TABLE [dbo].[seq_invoice_year](
[year] [numeric](4, 0) NOT NULL,
[next_val] [numeric](19, 0) NULL,
PRIMARY KEY CLUSTERED (  [year] ASC)
);

Insert into [dbo].[seq_invoice_year] values(2021,NULL);
Insert into [dbo].[seq_invoice_year] values(2022,NULL);
Insert into [dbo].[seq_invoice_year] values(2023,NULL);
Insert into [dbo].[seq_invoice_year] values(2024,NULL);


ALTER PROCEDURE [dbo].[GET_NEXT_NUMBER]
    @YYYY Int,
    @ret_val bigInt OUT
AS
    SET XACT_ABORT ON;

    Begin TRY
        begin tran
            select @ret_val = next_val  from seq_invoice_year  with (updlock, rowlock) where [year] = @YYYY;
            update seq_invoice_year set next_val= coalesce(@ret_val,0) +1  where  [year] = @YYYY and (next_val=@ret_val or next_val is null)
            Select @ret_val = coalesce(@ret_val,0) +1
        commit
    END TRY
    BEGIN CATCH
        rollback
        declare @errmsg nvarchar(max) = ERROR_MESSAGE() + ',Err code:'+Ltrim(Str(ERROR_NUMBER())  )+', Line:'+Ltrim(Str(ERROR_LINE()) )
        declare @errsev int = ERROR_SEVERITY()
        declare @errst int = ERROR_STATE()
        RAISERROR( @errmsg,@errsev,@errst)
        RAISERROR ('=============================================================================================================',10,1);
        RAISERROR ('Zmiany NIE zostały wprowadzone , wykonano ROLLBACK !',16,1);
        RAISERROR ('=============================================================================================================',10,1);
    END CATCH
    RETURN;
